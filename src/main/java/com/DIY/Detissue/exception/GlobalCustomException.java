package com.DIY.Detissue.exception;

import com.DIY.Detissue.payload.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalCustomException {

//  Nơi đăng ký các Exception sẽ kích hoạt code bên trong hàm
    @ExceptionHandler(CustomFileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFound(Exception e){

        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(Exception e){

        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(Exception e){
        BaseResponse response = new BaseResponse();
        response.setStatusCode(500);
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
