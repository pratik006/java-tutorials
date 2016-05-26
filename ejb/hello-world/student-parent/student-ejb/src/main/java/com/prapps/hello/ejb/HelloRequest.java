package com.prapps.hello.ejb;

import java.io.Serializable;

public class HelloRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String key;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
