package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(ProductNotFoundException exception) {
        return new ErrorResponse("PRODUCT_NOT_FOUND", exception.getMessage(), Collections.emptyList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<FieldError> details = exception.getBindingResult()
                .getFieldErrors();
        return new ErrorResponse("VALIDATION_ERROR", "Validation failed", details);
    }

    @ExceptionHandler(InvalidProductCategoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidProductCategoryException(InvalidProductCategoryException exception) {
        return new ErrorResponse("PRODUCT_CATEGORY_ERROR", exception.getMessage(), Collections.emptyList());
    }
}
