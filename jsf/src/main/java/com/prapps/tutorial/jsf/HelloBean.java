package com.prapps.tutorial.jsf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String desc;
	private GenderType genderType;
	private Collection<GenderType> genderTypes = Arrays.asList(GenderType.MALE, GenderType.FEMALE);

	public void save() {
		System.out.println(genderType);
	}
	
	public void validate(FacesContext ctx, UIComponent comp, Object obj) {
		System.out.println("validating..."+desc);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}

	public void setDesc(String disc) {
		this.desc = disc;
	}

	public GenderType getGenderType() {
		return genderType;
	}
	public void setGenderType(GenderType genderType) {
		this.genderType = genderType;
	}
	public Collection<GenderType> getGenderTypes() {
		return genderTypes;
	}
	public void setGenderTypes(Collection<GenderType> genderTypes) {
		this.genderTypes = genderTypes;
	}
}
