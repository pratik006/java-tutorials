package com.prapps.tutorial.ejb.rest;

public enum ResponseStatusType {
	SUCCESS("success"),
	FAILURE("failed");
	
	private String status;

	ResponseStatusType(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String responseStatus) {
		this.status = responseStatus;
	}
}
