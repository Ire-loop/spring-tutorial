package com.example.demo.exception;

public class InvalidProductCategoryException extends RuntimeException {
    public InvalidProductCategoryException(String message) {
        super(message);
    }
}
