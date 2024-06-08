package com.DIY.Detissue.service.Imp;

import com.DIY.Detissue.payload.response.ProductResponse;

import java.util.List;

public interface UserWishlistServiceImp {
    List<ProductResponse> findUserWishListByUserId(int id);
}
