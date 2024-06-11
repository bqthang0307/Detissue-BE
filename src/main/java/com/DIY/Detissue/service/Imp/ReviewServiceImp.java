package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.ReviewRequest;
import com.DIY.Detissue.payload.response.ReviewResponse;

import java.util.List;

public interface ReviewServiceImp {
    List<ReviewResponse> findByProductId(int id);
    boolean addReview(ReviewRequest request);
}
