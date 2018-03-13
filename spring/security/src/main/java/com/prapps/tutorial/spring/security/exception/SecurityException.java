package com.prapps.tutorial.spring.security.exception;

public class SecurityException extends Exception {
	private static final long serialVersionUID = 1L;

	private Throwable throwable;
	private String msg;
	
	public SecurityException(Throwable throwable) {
		this.throwable = throwable;
	}

	public SecurityException(Throwable throwable, String msg) {
		this.throwable = throwable;
		this.msg = msg;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
