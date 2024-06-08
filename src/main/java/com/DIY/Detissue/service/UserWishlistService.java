package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.repository.ProductSkusRepository;
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
            throw new CustomException("Error findUserWishListByUserId in ProductService " + e.getMessage());
        }
        return responses;
    }
}
