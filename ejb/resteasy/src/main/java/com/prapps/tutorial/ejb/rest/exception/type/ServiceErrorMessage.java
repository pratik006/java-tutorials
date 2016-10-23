package com.prapps.tutorial.ejb.rest.exception.type;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceErrorMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement
	private int errorCode;
	@XmlElement
	private String errorMsg;
	
	public ServiceErrorMessage() { }
	
	public ServiceErrorMessage(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public ServiceErrorMessage(int errorCode, String errorMsg) {
		this(errorCode);
		this.errorMsg = errorMsg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
