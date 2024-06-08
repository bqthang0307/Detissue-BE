package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.entity.User;
import com.DIY.Detissue.entity.UserWishlist;
import com.DIY.Detissue.entity.UserWishlistId;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.repository.ProductRepository;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.repository.UserRepository;
import com.DIY.Detissue.repository.UserWishlistRepository;
import com.DIY.Detissue.service.Imp.UserWishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWishlistService implements UserWishlistServiceImp {
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private UserWishlistRepository userWishlistRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<ProductResponse> findUserWishListByUserId(int id) {
        List<ProductResponse> responses = new ArrayList<>();
        try {
            List<Product> list = userWishlistRepository.findUserWishListByUserId(id);
            for (Product product : list) {
                ProductResponse response = new ProductResponse();
                response.setId(product.getId());
                response.setName(product.getName());
                response.setShortDesc(product.getShortDesc());
                response.setFullDesc(product.getFullDesc());
                response.setImage(product.getImage());
                response.setCategory(product.getCategory().getName());

                Long maxPriceLong = productSkusRepository.findByProductIdWithMaxPrice(product.getId());
                long maxPrice = (maxPriceLong != null) ? maxPriceLong : 0;

                Long minPriceLong = productSkusRepository.findByProductIdWithMinPrice(product.getId());
                long minPrice = (minPriceLong != null) ? minPriceLong : 0;

                response.setPriceMax(maxPrice);
                response.setPriceMin(minPrice);

                responses.add(response);
            }
        } catch (Exception e) {
            throw new CustomException("Error findUserWishListByUserId in UserWishlistService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public boolean deleteProductFromWishlist(int userId, int productId) {
        try {
            UserWishlist userWishlist = userWishlistRepository.findByUserIdAndProductId(userId, productId);
            if (userWishlist == null) throw new CustomException("Product not found in wishlist");
            userWishlistRepository.delete(userWishlist);
        } catch (Exception e) {
            throw new CustomException("Error deleteProductFromWishlist in ProductService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean addUserWishlist(int userId, int productId) {
        try {
            UserWishlist userWishlist = new UserWishlist();

            User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User not found"));
            Product product = productRepository.findById(productId).orElseThrow(() -> new CustomException("Product not found"));

            userWishlist.setUser(user);
            userWishlist.setProduct(product);

            UserWishlistId userWishlistId = new UserWishlistId();
            userWishlistId.setUserId(userId);
            userWishlistId.setProductId(productId);
            userWishlist.setId(userWishlistId);
            userWishlistRepository.save(userWishlist);
        } catch (Exception e) {
            throw new CustomException("Error addUserWishlist in ProductService " + e.getMessage());
        }
        return true;
    }
}
