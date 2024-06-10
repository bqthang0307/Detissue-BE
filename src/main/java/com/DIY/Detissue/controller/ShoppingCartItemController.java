package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;
import com.DIY.Detissue.service.Imp.ShoppingCartItemServiceImp;
import com.DIY.Detissue.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartItemController {
    @Autowired
    private ShoppingCartItemServiceImp shoppingCartItemServiceImp;
    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("user")
    ResponseEntity<?> findShoppingCartItemByUserId(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);
        List<ShoppingCartItemResponse> list = shoppingCartItemServiceImp.findByUserId(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("add")
    ResponseEntity<?> addShoppingCartItem(@RequestHeader("Authorization") String token, @RequestParam int productId, @RequestParam int quantity) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);
        List<ShoppingCartItemResponse> list = shoppingCartItemServiceImp.findByUserId(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("delete")
    ResponseEntity<?> deleteShopingCartItemById(@RequestHeader("Authorization") String token, @RequestParam int id) {
        token = token.substring(7);
        int userId = jwtHelper.getUserIdFromToken(token);
        boolean result = shoppingCartItemServiceImp.deleteShopingCartItemById(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
