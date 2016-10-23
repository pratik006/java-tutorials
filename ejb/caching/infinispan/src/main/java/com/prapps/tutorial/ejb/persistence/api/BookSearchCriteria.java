package com.prapps.tutorial.ejb.persistence.api;

import java.io.Serializable;

public class BookSearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String isbn;
	private String title;
	private String author;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
