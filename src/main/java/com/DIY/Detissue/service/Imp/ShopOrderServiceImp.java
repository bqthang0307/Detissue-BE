package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.ShopOrderRequest;
import com.DIY.Detissue.payload.response.ShopOrderResponse;

import java.util.List;

public interface ShopOrderServiceImp {
    List<ShopOrderResponse> findByUserId(int id);

    boolean addShopOrder(ShopOrderRequest request);
}
