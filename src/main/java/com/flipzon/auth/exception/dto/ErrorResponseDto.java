package com.flipzon.auth.exception.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String traceId;
}
