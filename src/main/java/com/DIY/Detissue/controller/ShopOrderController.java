package com.DIY.Detissue.controller;

import com.DIY.Detissue.exception.CustomException;
import com.DIY.Detissue.payload.request.ShopOrderRequest;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping("add")
    ResponseEntity<?> addShopOrder(@RequestHeader("Authorization") String token, @Valid ShopOrderRequest request, BindingResult bindingResult){
        List<FieldError> listError = bindingResult.getFieldErrors();
        for (FieldError errors : listError) {
            throw new RuntimeException(errors.getDefaultMessage());
        }

        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);
        request.setUserId(id);

        boolean result = shopOrderServiceImp.addShopOrder(request);

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
