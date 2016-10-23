package com.prapps.tutorial.ejb.persistence.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.prapps.tutorial.ejb.persistence.BookEntity;
import com.prapps.tutorial.ejb.rest.model.Book;

public class BookMapper {

	public Book map(BookEntity entity) {
		if (entity == null) {
			return null;
		}
		
		Book book = new Book();
		book.setAuthor(entity.getAuthor());
		book.setIsbn(entity.getIsbn());
		book.setPublishedDate(entity.getPublishedDate());
		book.setTitle(entity.getTitle());
		return book;
	}
	
	public List<Book> map(Collection<BookEntity> entities) {
		List<Book> books = new ArrayList<>(entities.size());
		for (BookEntity entity : entities) {
			books.add(map(entity));
		}
		return books;
	}
	
	public BookEntity map(Book book) {
		BookEntity entity = new BookEntity();
		entity.setAuthor(book.getAuthor());
		entity.setIsbn(book.getIsbn());
		entity.setPublishedDate(book.getPublishedDate());
		entity.setTitle(book.getTitle());
		return entity;
	}
}
