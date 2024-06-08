package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ProductResponse;
import com.DIY.Detissue.service.Imp.UserWishlistServiceImp;
import com.DIY.Detissue.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-wishlist")
public class UserWishlistController {
    @Autowired
    private UserWishlistServiceImp userWishlistServiceImp;
    @Autowired
    private JwtHelper jwtHelper;
    @GetMapping("user")
    ResponseEntity<?> findUserWishListByUserId(@RequestParam int id){
        List<ProductResponse> list = userWishlistServiceImp.findUserWishListByUserId(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("delete")
    ResponseEntity<?> deleteUserWishlist(
            @RequestHeader("Authorization") String token,
            @RequestParam int productId) {
        token = token.substring(7);
        int userId = jwtHelper.getUserIdFromToken(token);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userWishlistServiceImp.deleteProductFromWishlist(userId, productId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("add")
    ResponseEntity<?> addUserWishlist(
            @RequestHeader("Authorization") String token,
            @RequestParam int productId) {
        token = token.substring(7);
        int userId = jwtHelper.getUserIdFromToken(token);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(userWishlistServiceImp.addUserWishlist(userId, productId));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
