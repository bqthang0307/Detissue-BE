package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.ProductSkusResponse;

import java.util.List;

public interface ProductSkusServiceImp {
    List<ProductSkusResponse> findByProductId(int id);
}
