package com.example.demo.exception.types;

import com.example.demo.exception.base.ApiException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ApiException {
    public DuplicateEmailException(String email) {
        super("Email already exists: " + email, HttpStatus.CONFLICT.value());
    }
}