package com.sparta.board.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private final String message;
    private final int status;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode){
        return ResponseEntity.status(errorCode.getHttpStatus()).body(ErrorResponse.builder().message(errorCode.getDetail()).status(errorCode.getHttpStatus().value()).build());
    }

    public static ResponseEntity<ErrorResponse> toResponseEntityValid(String errorCode, HttpStatus httpStatus){
        return ResponseEntity.status(httpStatus.value()).body(ErrorResponse.builder().message(errorCode).status(httpStatus.value()).build());
    }
}
