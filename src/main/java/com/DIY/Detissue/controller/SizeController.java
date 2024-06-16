package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.service.Imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("size")
public class SizeController {
    @Autowired
    private SizeServiceImp sizeServiceImp;

    @GetMapping("product")
    ResponseEntity<?> findByProductId(int id) {

        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(sizeServiceImp.findByProductId(id));

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
