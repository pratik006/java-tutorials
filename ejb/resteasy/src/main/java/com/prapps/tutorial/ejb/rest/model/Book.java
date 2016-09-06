package com.prapps.tutorial.ejb.rest.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="book")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(required=true) 
	private String isbn;
	@XmlElement(required=true) 
	private String title;
	@XmlElement(required=true) 
	private String author;
	@XmlElement(required=true)
	private Calendar publishedDate;
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public Calendar getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Calendar publishedDate) {
		this.publishedDate = publishedDate;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if (otherObject instanceof Book) {
			Book other = (Book) otherObject;
			return isbn.equals(other.getIsbn()) && title.equals(other.getTitle());
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", publishedDate=" + publishedDate
				+ "]";
	}
}
