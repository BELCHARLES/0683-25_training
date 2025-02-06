package com.exception;

public class BoundaryCheckException extends Exception {
    public  BoundaryCheckException(String message) {
        super(message);
    }

    public  BoundaryCheckException(String message,Throwable cause) {
        super(message,cause);
    }

    public BoundaryCheckException (Throwable cause){
        super(cause);
    }
}
