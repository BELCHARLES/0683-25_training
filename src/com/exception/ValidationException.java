package com.exception;

public class ValidationException extends Exception {
    private final String errorCode;

    public ValidationException(String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

