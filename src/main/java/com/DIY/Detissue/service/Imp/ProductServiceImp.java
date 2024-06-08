package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.entity.Product;
import com.DIY.Detissue.payload.response.ProductResponse;

import java.util.List;

public interface ProductServiceImp {
    List<ProductResponse> findAllProduct(int page, int size);
    List<ProductResponse> findProductByCategory(int page, int size, int id);
    ProductResponse findById(int id);
}
