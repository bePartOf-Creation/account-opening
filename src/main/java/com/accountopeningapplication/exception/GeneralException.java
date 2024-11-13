package com.accountopeningapplication.exception;

import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    protected final String code;

    public GeneralException(String message, String code) {
        super(message);
        this.code = code;
    }
}
