package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CustomException{
    public ResourceNotFoundException(String message){
        super(ErrorCode.USER_NOT_FOUND,message, HttpStatus.NOT_FOUND);
    }
}
