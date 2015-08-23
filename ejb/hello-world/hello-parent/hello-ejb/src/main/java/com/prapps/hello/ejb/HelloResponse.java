package com.prapps.hello.ejb;

import java.io.Serializable;

public class HelloResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String resp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResp() {
		return resp;
	}
	public void setResp(String resp) {
		this.resp = resp;
	}
	
}
