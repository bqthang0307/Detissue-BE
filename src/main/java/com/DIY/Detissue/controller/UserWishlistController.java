package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.service.Imp.UserWishlistServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-wishlist")
public class UserWishlistController {
    @Autowired
    private UserWishlistServiceImp userWishlistServiceImp;
    @GetMapping("user")
    ResponseEntity<?> findUserWishListByUserId(@RequestParam int id){
        List<ProductResponse> list = userWishlistServiceImp.findUserWishListByUserId(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
