package com.prapps.tutorial.rest;

import java.util.Collection;
import java.util.Collections;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.prapps.tutorial.rest.dto.Book;

@Component
@Path("/library")
public class LibraryRestService {

	@GET
	@Path("/books")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Collection<Book> getBooks() {
		Book book = new Book();
		book.setName("My Gita");
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("132465");
		return Collections.singletonList(book);
	}
}
