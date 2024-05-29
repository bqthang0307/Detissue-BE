package com.DIY.Detissue.exception;

public class CustomFileNotFoundException extends RuntimeException{

    private int status;
    private String message;

    CustomFileNotFoundException(){}
    public CustomFileNotFoundException(int status, String message){
        this.status = status;
        this.message = message;
    }

}
