package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.CreateShopOrderRequest;
import com.DIY.Detissue.payload.response.ShopOrderResponse;

import java.util.List;

public interface ShopOrderServiceImp {
    List<ShopOrderResponse> findByUserId(int id);

    boolean addShopOrder(CreateShopOrderRequest request);
    boolean updateShopOrderStatus(int id, int status, int userId);

    List<ShopOrderResponse> findAllShopOrder(int page, int size);
    ShopOrderResponse findById(int id);
}
