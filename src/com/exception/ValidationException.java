package com.exception;

public class ValidationException extends Exception {
    private static final long serialVersionUID = 1L;
	private final String errorCode;
    private final String field;
    private final String additionalMsg;
    
    public ValidationException(String errorCode ){
        super(errorCode);
        this.errorCode = errorCode;
		this.field = "";
		this.additionalMsg = "";
    }
    
    public ValidationException(String errorCode,String field){
        super(errorCode);
        this.errorCode = errorCode;
		this.field = field;
		this.additionalMsg = "";
    }

    public ValidationException(String errorCode,String field,String additionalMsg ){
        super(errorCode);
        this.errorCode = errorCode;
		this.field = field;
		this.additionalMsg = additionalMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }
    
    public String getField() {
        return field;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }

}

