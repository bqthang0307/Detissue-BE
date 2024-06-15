package com.DIY.Detissue.service;

import com.DIY.Detissue.entity.Image;
import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.entity.ProductSkus;
import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.CreateProductRequest;
import com.DIY.Detissue.payload.request.UpdateProductRequest;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.repository.*;
import com.DIY.Detissue.service.Imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SizeRepository sizeRepository;
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
    public boolean addProduct(CreateProductRequest product) {
        try {
            Product newProduct = new Product();
            newProduct.setName(product.getName());
            newProduct.setShortDesc(product.getShortDesc());
            newProduct.setFullDesc(product.getFullDesc());
            newProduct.setCategory(categoryRepository.findById(product.getCategoryId()).get());
            productRepository.save(newProduct);

            product.getProductSizes().forEach(productSize -> {
                ProductSkus productSkus = new ProductSkus();
                productSkus.setProduct(newProduct);
                productSkus.setPrice(productSize.getPrice());
                productSkus.setSize(sizeRepository.findById(productSize.getSizeId()).get());
                productSkus.setStockQuantity(productSize.getQuantity());
                productSkusRepository.save(productSkus);
            });
        } catch (Exception e) {
            throw new CustomException("Error addProduct in ProductService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateProduct(UpdateProductRequest updateProduct) {
        try {
            Product product = productRepository.findById(updateProduct.getId()).get();
            updateProduct.setName(updateProduct.getName());
            product.setShortDesc(updateProduct.getShortDesc());
            product.setFullDesc(updateProduct.getFullDesc());
            product.setCategory(categoryRepository.findById(updateProduct.getCategoryId()).get());
            productRepository.save(product);
        } catch (Exception e) {
            throw new CustomException("Error updateProduct in ProductService " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean deleteProduct(int id) {
        try {
            productSkusRepository.findByProductId(id).forEach(productSkusRepository::delete);
            productRepository.deleteById(id);

        } catch (Exception e) {
            throw new CustomException("Error deleteProduct in ProductService " + e.getMessage());
        }
        return true;
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

    /**
     * This method creates a ProductResponse object from a Product object.
     * It sets the ID, name, short description, full description, and category of the product.
     * It also retrieves a list of images associated with the product and sets it in the response.
     * Finally, it sets the maximum and minimum price of the product in the response.
     *
     * @param product The Product object to be transformed into a ProductResponse.
     * @return A ProductResponse object containing the details of the product.
     */

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
