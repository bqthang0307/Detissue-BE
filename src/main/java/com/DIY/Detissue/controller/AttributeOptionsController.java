package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.service.Imp.AttributeOptionsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("attribute-options")
public class AttributeOptionsController {
    @Autowired
    private AttributeOptionsServiceImp attributeOptionsServiceImp;
    @GetMapping("")
    private ResponseEntity<?> findByProductSkusIdAndAttributeId(int productId, int attributeId) {
        BaseResponse response = new BaseResponse();
        response.setData(attributeOptionsServiceImp.findByProductIdAndAttributeId(productId, attributeId));
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
