package com.crud.springbootcrud.exception;

public class AuthenticationError extends RuntimeException {

    public AuthenticationError(String message) {
        super(message);
    }
}
