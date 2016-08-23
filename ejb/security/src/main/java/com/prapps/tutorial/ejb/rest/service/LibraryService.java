package com.prapps.tutorial.ejb.rest.service;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import com.prapps.tutorial.ejb.rest.interceptor.LogInterceptor;
import com.prapps.tutorial.ejb.rest.interceptor.SecurityInterceptor;
import com.prapps.tutorial.ejb.rest.model.Book;

@Path("/library")
@Interceptors({LogInterceptor.class, SecurityInterceptor.class})
public class LibraryService {

	@EJB LibraryEjb libraryEjb;
	
	@GET
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public List<Book> getBooks() {
		return libraryEjb.getBooks();
	}
	
	@GET
	@Path("/books/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("isbn") String id) {
		return libraryEjb.getBook(id);
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
