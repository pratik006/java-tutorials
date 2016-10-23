package com.prapps.tutorial.ejb.rest.exception.type;

public enum ErrorCodeType {
	UNKNOWN_ERROR(-999),
	MANDATORY_ISBN(1001),
	MANDATORY_AUTHOR(1002);
	
	private int errorCode;
	
	private ErrorCodeType(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
