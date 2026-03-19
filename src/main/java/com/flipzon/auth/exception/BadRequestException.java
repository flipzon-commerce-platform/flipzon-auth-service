package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadRequestException extends CustomException{
    public BadRequestException(String message){
        super(ErrorCode.BAD_REQUEST,message, HttpStatus.BAD_REQUEST);
    }
}
