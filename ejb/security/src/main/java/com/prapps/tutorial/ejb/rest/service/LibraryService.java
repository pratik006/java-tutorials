package com.prapps.tutorial.ejb.rest.service;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import com.prapps.tutorial.ejb.rest.model.Book;

@Path("/library")
public class LibraryService {
	private static Logger LOG = Logger.getLogger(LibraryService.class.getName());
	
	@EJB LibraryEjb libraryEjb;
	
	@RolesAllowed("admin")
	@GET
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public List<Book> getBooks() {
		LOG.info("within LibraryService->getBooks");
		return libraryEjb.getBooks();
	}
	
	@RolesAllowed("user")
	@GET
	@Path("/book/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("isbn") String isbn) {
		return libraryEjb.getBook(isbn);
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
