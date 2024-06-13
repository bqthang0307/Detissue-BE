package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.repository.ProductRepository;
import com.DIY.Detissue.repository.ProductSkusRepository;
import com.DIY.Detissue.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSkusRepository productSkusRepository;

    @Override
    public List<ProductResponse> findAllProduct(int page, int size) {
        List<ProductResponse> responses = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> list = productRepository.findAllProductByPaging(pageable);
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
            throw new CustomException("Error findAllProduct in ProductService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public List<ProductResponse> findProductByCategory(int page, int size, int id) {
        List<ProductResponse> responses = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> list = productRepository.findByCategoryId(id, pageable);
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
            throw new CustomException("Error findAllProduct in ProductService " + e.getMessage());
        }
        return responses;
    }

    @Override
    public ProductResponse findById(int id) {
        ProductResponse response = new ProductResponse();
        try {
            Product product = productRepository.findProductById(id);
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
        } catch (Exception e) {
            throw new CustomException("Error findProductBySearch in ProductService " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<ProductResponse> findProductBySearch(String Search, int page, int size) {
        List<ProductResponse> responses = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(page, size);
            List<Product> list = productRepository.findByNameContaining(Search, pageable);
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
            throw new CustomException("Error findProductBySearch in ProductService " + e.getMessage());
        }
        return responses;
    }
}
