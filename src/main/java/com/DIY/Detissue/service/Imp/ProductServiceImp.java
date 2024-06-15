package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.CreateProductRequest;
import com.DIY.Detissue.payload.request.UpdateProductRequest;
import com.DIY.Detissue.payload.response.ProductResponse;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductServiceImp {
    List<ProductResponse> findAllProduct(int page, int size);
    List<ProductResponse> findProductByCategory(int page, int size, int id);
    ProductResponse findById(int id);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<ProductResponse> findProductBySearch(String Search, int page, int size);

    boolean addProduct(CreateProductRequest product);
    boolean updateProduct(UpdateProductRequest product);
    boolean deleteProduct(int id);
}
