package com.prapps.tutorial.jsf;

public enum GenderType {
	MALE("M", "Male"),
	FEMALE("F", "Female");
	
	private String code;
	private String desc;
	
	private GenderType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
}
