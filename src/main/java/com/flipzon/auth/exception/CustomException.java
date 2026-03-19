package com.flipzon.auth.exception;

import com.flipzon.auth.exception.enums.ErrorCode;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
    private final HttpStatus status;

    public CustomException(ErrorCode errorCode, String message, HttpStatus status){
        super(message);
        this.errorCode=errorCode;
        this.status=status;
    }
}
