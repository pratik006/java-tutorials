package com.prapps.tutorial.ejb.rest.service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.PathParam;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import com.prapps.tutorial.ejb.rest.interceptor.LogInterceptor;
import com.prapps.tutorial.ejb.rest.model.Book;

@SecurityDomain("prapps")
@RolesAllowed({"user", "admin"})
@Stateless
@Interceptors({LogInterceptor.class})
@WebContext(contextRoot="/", authMethod = "BASIC", transportGuarantee="NONE")
public class LibraryEjb {
	private static Logger LOG = Logger.getLogger(LibraryEjb.class.getName());
	@Resource
    private SessionContext ctx;

	@RolesAllowed("admin")
	public List<Book> getBooks() {
		Principal principal = ctx.getCallerPrincipal();
        LOG.fine("principal: "+principal.getName());
        
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
