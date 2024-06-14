package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Image;
import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.repository.ImagesRepository;
import com.DIY.Detissue.repository.ProductRepository;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkusRepository productSkusRepository;

    @Autowired
    private ImagesRepository imagesRepository;
    @Override
    public List<ProductResponse> findAllProduct(int page, int size) {
        List<ProductResponse> responses;
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> products = productRepository.findAllProductByPaging(pageable);
            responses = products.stream().map(this::createProductResponse).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Error findAllProduct in ProductService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public List<ProductResponse> findProductByCategory(int page, int size, int id) {
        List<ProductResponse> responses;
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> products = productRepository.findByCategoryId(id, pageable);
            responses = products.stream().map(this::createProductResponse).collect(Collectors.toList());

        } catch (Exception e) {
            throw new CustomException("Error findAllProduct in ProductService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public ProductResponse findById(int id) {
        ProductResponse response;
        try {
            Product product = productRepository.findProductById(id);
            response = createProductResponse(product);

        } catch (Exception e) {
            throw new CustomException("Error findProductBySearch in ProductService " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<ProductResponse> findProductBySearch(String Search, int page, int size) {
        List<ProductResponse> responses;
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> products = productRepository.findByNameContaining(Search, pageable);
            responses = products.stream().map(this::createProductResponse).collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException("Error findProductBySearch in ProductService " + e.getMessage());
        }
        return responses;
    }

    private long getMaxPrice(int productId) {
        Long maxPriceLong = productSkusRepository.findByProductIdWithMaxPrice(productId);
        return (maxPriceLong != null) ? maxPriceLong : 0;
    }

    private long getMinPrice(int productId) {
        Long minPriceLong = productSkusRepository.findByProductIdWithMinPrice(productId);
        return (minPriceLong != null) ? minPriceLong : 0;
    }
    private ProductResponse createProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setShortDesc(product.getShortDesc());
        response.setFullDesc(product.getFullDesc());
        response.setCategory(product.getCategory().getName());

        List<String> imageList = imagesRepository.findByProductId(product.getId()).stream()
                .map(Image::getSource)
                .collect(Collectors.toList());
        response.setImage(imageList);

        response.setPriceMax(getMaxPrice(product.getId()));
        response.setPriceMin(getMinPrice(product.getId()));

        return response;
    }
}
