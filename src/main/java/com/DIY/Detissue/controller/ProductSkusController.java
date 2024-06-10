package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.service.Imp.ProductSkusServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-skus")
public class ProductSkusController {
    @Autowired
    private ProductSkusServiceImp productSkusServiceImp;

    @GetMapping("")
    private ResponseEntity<?> findByProductId(int id) {

        BaseResponse response = new BaseResponse();
        response.setData(productSkusServiceImp.findByProductId(id));
        response.setStatusCode(200);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
