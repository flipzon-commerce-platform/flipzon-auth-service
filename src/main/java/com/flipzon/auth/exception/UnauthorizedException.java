package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException(String message){
        super(ErrorCode.UNAUTHORIZED, message, HttpStatus.UNAUTHORIZED);
    }
}
