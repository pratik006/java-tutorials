package com.prapps.tutorial.ejb.rest;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/library")
public class LibraryService {

	@GET
	public List<Book> hello() {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return Collections.singletonList(book);
	}
	
	@GET
	@Path("/books")
	public List<Book> getBooks() {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return Collections.singletonList(book);
	}

	@GET
	@Path("/book/{isbn}")
	public String getBook(@PathParam("isbn") String id) {
		return "testing";
	}

	@PUT
	@Path("/book/{isbn}")
	public void addBook(@PathParam("isbn") String id, @QueryParam("name") String name) {

	}

	@DELETE
	@Path("/book/{id}")
	public void removeBook(@PathParam("id") String id) {

	}
}
