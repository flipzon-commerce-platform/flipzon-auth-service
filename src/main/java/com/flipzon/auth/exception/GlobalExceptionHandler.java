package com.flipzon.auth.exception;

import com.flipzon.auth.exception.dto.ErrorResponseDto;
import com.flipzon.auth.exception.enums.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException ex, HttpServletRequest request){
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .timeStamp(LocalDateTime.now().toString())
                .status(ex.getStatus().value())
                .error(ex.getErrorCode().name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .traceId(UUID.randomUUID().toString())
                .build();
        return new ResponseEntity<>(responseDto,ex.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, HttpServletRequest request){
//        ErrorResponseDto responseDto = ErrorResponseDto.builder()
//                .timeStamp(LocalDateTime.now().toString())
//                .status(500)
//                .error(ErrorCode.INTERNAL_SERVER_ERROR.name())
//                .message(ex.getMessage())
//                .path(request.getRequestURI())
//                .traceId(UUID.randomUUID().toString())
//                .build();
//        return ResponseEntity.internalServerError().body(responseDto);
//    }
}
