package com.prapps.tutorial.ejb.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.prapps.tutorial.ejb.rest.ResponseStatusType;
import com.prapps.tutorial.ejb.rest.exception.type.ServiceErrorMessage;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private String status;
	@XmlElement
	private Collection<ServiceErrorMessage> errors;
	
	public BaseResponse() { }
	
	public String getStatus() {
		return status;
	}

	public void setStatus(ResponseStatusType statusType) {
		this.status = statusType.getStatus();
	}

	public Collection<ServiceErrorMessage> getErrors() {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		
		return errors;
	}

	public void setErrors(Collection<ServiceErrorMessage> errors) {
		this.errors = errors;
	}
}
