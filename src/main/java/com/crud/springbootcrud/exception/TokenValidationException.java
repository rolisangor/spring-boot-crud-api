package com.crud.springbootcrud.exception;

public class TokenValidationException extends RuntimeException{

    public TokenValidationException(String message) {
        super(message);
    }
}
