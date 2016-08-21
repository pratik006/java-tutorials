package com.prapps.tutorial.ejb.rest.service;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.prapps.tutorial.ejb.rest.model.Book;

@Path("/library")
public class LibraryService {

	@GET
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public List<Book> getBooks() {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return Collections.singletonList(book);
	}
	
	@GET
	@Path("/books/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("isbn") String id) {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return book;
	}
	
	@GET
	@Path("/books/{author}/{title}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("author") String author, @PathParam("title") String title) {
		Book book = new Book();
		book.setAuthor(author);
		book.setIsbn("123asd");
		book.setTitle(title);
		return book;
	}
}
