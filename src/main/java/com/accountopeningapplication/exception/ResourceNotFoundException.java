package com.accountopeningapplication.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    protected final String code;

    public ResourceNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}
