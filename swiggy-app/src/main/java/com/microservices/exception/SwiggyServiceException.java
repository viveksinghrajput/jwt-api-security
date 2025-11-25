package com.microservices.exception;

public class SwiggyServiceException extends RuntimeException{
    public SwiggyServiceException(String message) {
        super(message);
    }
}
