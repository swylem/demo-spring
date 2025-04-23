package com.example.demo.exception.handler;

import com.example.demo.exception.base.ApiException;
import com.example.demo.exception.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getStatusCode(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOther(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                500,
                "Something went wrong: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.internalServerError().body(error);
    }
}