package com.DIY.Detissue.controller;

import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.payload.response.ShopOrderResponse;
import com.DIY.Detissue.payload.response.ShoppingCartItemResponse;
import com.DIY.Detissue.service.Imp.ShopOrderServiceImp;
import com.DIY.Detissue.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop-order")
public class ShopOrderController {
    @Autowired
    private ShopOrderServiceImp shopOrderServiceImp;
    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("user")
    ResponseEntity<?> findShoppingCartItemByUserId(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);

        List<ShopOrderResponse> list = shopOrderServiceImp.findByUserId(id);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(list);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
