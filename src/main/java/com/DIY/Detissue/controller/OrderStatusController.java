package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.service.Imp.OrderStatusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order-status")
public class OrderStatusController {
    @Autowired
    private OrderStatusServiceImp orderStatusServiceImp;

    @GetMapping("")
    public ResponseEntity<?> findAllByShopOrderId() {

        BaseResponse response = new BaseResponse();
        response.setData(orderStatusServiceImp.findAll());
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
