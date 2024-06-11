package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Image;
import com.DIY.Detissue.entity.ProductSkus;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ImageResponse;
import com.DIY.Detissue.payload.response.ProductSkusResponse;
import com.DIY.Detissue.repository.ImagesRepository;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.service.Imp.ProductSkusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSkusService implements ProductSkusServiceImp {
    @Autowired
    private ProductSkusRepository productSkusRepository;
    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public List<ProductSkusResponse> findByProductId(int id) {
        List<ProductSkusResponse> responses = new ArrayList<>();
        try {
            List<ProductSkus> productSkus = productSkusRepository.findByProductId(id);
            for (ProductSkus productSku : productSkus) {
                ProductSkusResponse response = new ProductSkusResponse();
                response.setId(productSku.getId());
                response.setPrice(productSku.getPrice());
                response.setQuantity(productSku.getStockQuantity());
                List<Image> images = imagesRepository.findByProductSkusId(productSku.getId());
                List<String> imageResponses = new ArrayList<>();
                for (Image image : images) {
                    String imageUrl = image.getSource();
                    imageResponses.add(imageUrl);
                }
                response.setImages(imageResponses);
                responses.add(response);
            }


        } catch (Exception e) {
            throw new CustomException("Error findByProductId in ProductSkusService " + e.getMessage());
        }
        return responses;
    }
}
