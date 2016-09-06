package com.prapps.tutorial.ejb.rest.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.prapps.tutorial.ejb.rest.model.Book;

@Path("/library")
public class LibraryService {
	private static final Log LOG = LogFactory.getLog(LibraryService.class);
	private static Map<String, Book> store = new HashMap<>();
	
	@GET
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public Collection<Book> getBooks() {
		return store.values();
	}
	
	@GET
	@Path("/books/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("isbn") String isbn) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("retrieving isbn: "+isbn);
		}
		return store.get(isbn);
	}
	
	@GET
	@Path("/books/{author}/{title}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("author") String author, @PathParam("title") String title) {
		for (Book book : store.values()) {
			if (author.equals(book.getAuthor()) && title.equals(book.getTitle())) {
				return book;
			}
		}
		return null;
	}
	
	@PUT
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public void addBook(Book book) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("New book: " + book);
		}
		store.put(book.getIsbn(), book);
	}
}
