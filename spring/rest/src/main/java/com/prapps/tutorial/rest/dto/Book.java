package com.prapps.tutorial.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlElement(required=true) 
	private String name;
	@XmlElement(required=true) 
	private String author;
	@XmlElement(required=true) 
	private String isbn;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public String toString() {
		return "Book [name=" + name + ", author=" + author + ", isbn=" + isbn + "]";
	}
}
