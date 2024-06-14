package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.request.AddProductToCartRequest;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;

import java.util.List;

public interface ShoppingCartItemServiceImp {
    List<ShoppingCartItemResponse> findByUserId(int id);
    boolean addShoppingCartItem(AddProductToCartRequest request);
    boolean deleteShopingCartItemById(int id);
}
