package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends CustomException{
    public InvalidCredentialsException(String message){
        super(ErrorCode.INVALID_CREDENTIALS,message, HttpStatus.BAD_REQUEST);
    }
}
