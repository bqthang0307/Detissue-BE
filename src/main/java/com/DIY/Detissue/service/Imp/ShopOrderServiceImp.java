package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.ShopOrderResponse;

import java.util.List;

public interface ShopOrderServiceImp {
    List<ShopOrderResponse> findByUserId(int id);
}
