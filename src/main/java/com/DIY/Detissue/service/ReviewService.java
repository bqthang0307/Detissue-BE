package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Review;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.ReviewRequest;
import com.DIY.Detissue.payload.response.ReviewResponse;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.repository.ReviewRepository;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.service.Imp.ReviewServiceImp;
import com.DIY.Detissue.utils.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements ReviewServiceImp {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DateHelper dateHelper;
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Override
    public List<ReviewResponse> findByProductId(int id) {
        List<ReviewResponse> responses = new ArrayList<>();
        try {
            List<Review> reviews = reviewRepository.findByProductId(id);
            for (Review review : reviews) {
                ReviewResponse response = new ReviewResponse();
                response.setId(review.getId());
                response.setRating(review.getRating());
                response.setReview(review.getReviews());
                response.setTimeCreated(review.getTimeCreated());
                response.setUsername(review.getUser().getUsername());
                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findByProductId in ReviewService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public boolean addReview(ReviewRequest request) {
        try {
            Review review = new Review();
            review.setRating(request.getRating());
            review.setReviews(request.getReview());
            review.setTimeCreated(dateHelper.getInternetTime().toString());
            review.setUser(userRepository.findById(request.getUserId()).get());
            review.setProductSkus((productSkusRepository.findByProductId(request.getProductId())).get(0));
            reviewRepository.save(review);
        } catch (Exception e) {
            throw new CustomException("Error addReview in ReviewService " + e.getMessage());
        }
        return true;
    }
}
