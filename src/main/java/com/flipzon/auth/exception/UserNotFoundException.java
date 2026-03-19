package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException{
    public UserNotFoundException(String message){
        super(ErrorCode.USER_NOT_FOUND,message, HttpStatus.NOT_FOUND);
    }
}
