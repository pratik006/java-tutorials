package com.prapps.tutorial.ejb.rest.exception;

import java.util.ArrayList;
import java.util.Collection;

import com.prapps.tutorial.ejb.rest.exception.type.ErrorCodeType;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private Collection<ErrorCodeType> errorCodeTypes;
	
	public ServiceException(ErrorCodeType errorCodeType) {
		this.errorCodeTypes = new ArrayList<>();
		this.errorCodeTypes.add(errorCodeType);
	}
	
	public ServiceException(Collection<ErrorCodeType> errorCodeTypes) {
		this.errorCodeTypes = errorCodeTypes;
	}
	
	public ServiceException(Throwable t, ErrorCodeType errorCodeType) {
		super(t);
		this.errorCodeTypes = new ArrayList<>();
		this.errorCodeTypes.add(errorCodeType);
	}

	public Collection<ErrorCodeType> getErrorCodeTypes() {
		return errorCodeTypes;
	}

	public void setErrorCodeTypes(Collection<ErrorCodeType> errorCodeTypes) {
		this.errorCodeTypes = errorCodeTypes;
	}
}
