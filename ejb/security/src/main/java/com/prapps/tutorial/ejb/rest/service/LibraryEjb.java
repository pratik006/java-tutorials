package com.prapps.tutorial.ejb.rest.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.PathParam;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import com.prapps.tutorial.ejb.rest.interceptor.LogInterceptor;
import com.prapps.tutorial.ejb.rest.interceptor.SecurityInterceptor;
import com.prapps.tutorial.ejb.rest.model.Book;

@SecurityDomain("sts")
@Stateless
@Interceptors({LogInterceptor.class, SecurityInterceptor.class})
@WebContext(contextRoot="/", authMethod = "BASIC", transportGuarantee="NONE")
public class LibraryEjb {

	@RolesAllowed("admin")
	public List<Book> getBooks() {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return Collections.singletonList(book);
	}
	
	@RolesAllowed("user")
	public Book getBook(@PathParam("isbn") String id) {
		Book book = new Book();
		book.setAuthor("Devdutt Patnaik");
		book.setIsbn("123asd");
		book.setTitle("My Gita");
		return book;
	}
}
