package com.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public ValidationException(String msg ){
        super(msg);
    }
    
    public ValidationException(String msg,Throwable cause) {
        super(msg,cause);
    }

}

