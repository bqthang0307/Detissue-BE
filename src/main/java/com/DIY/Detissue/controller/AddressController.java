package com.DIY.Detissue.controller;

import com.DIY.Detissue.payload.request.AddressRequest;
import com.DIY.Detissue.payload.response.BaseResponse;
import com.DIY.Detissue.service.Imp.AddressServiceImp;
import com.DIY.Detissue.utils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressServiceImp addressServiceImp;
    @Autowired
    private JwtHelper jwtHelper;
    @PostMapping("add")
    public ResponseEntity<?> addAddress(@RequestHeader("Authorization") String token,
                                        @Valid AddressRequest request,
                                        BindingResult bindingResult) {
        List<FieldError> listError = bindingResult.getFieldErrors();
        for (FieldError errors : listError) {
            throw new RuntimeException(errors.getDefaultMessage());
        }
        token = token.substring(7);
        int id = jwtHelper.getUserIdFromToken(token);
        request.setUserId(id);

        BaseResponse response = new BaseResponse();
        response.setData(addressServiceImp.addAddress(request));
        response.setStatusCode(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
