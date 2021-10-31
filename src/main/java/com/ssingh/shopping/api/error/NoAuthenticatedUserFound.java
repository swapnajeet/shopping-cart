package com.ssingh.shopping.api.error;

public class NoAuthenticatedUserFound extends RuntimeException{
    public NoAuthenticatedUserFound(String message) {
        super(message);
    }
}

