package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private Error error;
    private Timestamp timestamp;

    ErrorResponse(String code, String message, List<FieldError> fieldErrors) {
        this.error = new Error(code, message, fieldErrors.stream().map(FieldError::getDefaultMessage).toList());
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Data
    @AllArgsConstructor
    private static class Error {
        String code;
        String message;
        List<String> details;
    }
}


