package com.prapps.tutorial.ejb.rest.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.prapps.tutorial.ejb.persistence.api.BookSearchCriteria;
import com.prapps.tutorial.ejb.presistence.dao.LibraryDao;
import com.prapps.tutorial.ejb.rest.exception.ServiceException;
import com.prapps.tutorial.ejb.rest.exception.type.ErrorCodeType;
import com.prapps.tutorial.ejb.rest.model.Book;

@Path("/library")
public class LibraryService {
	private static final Logger LOG = Logger.getLogger(LibraryService.class.getName());
	
	@Inject private LibraryDao libraryDao;
	
	@GET
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public Collection<Book> getBooks() {
		BookSearchCriteria bookSearchCriteria = new BookSearchCriteria();
		return libraryDao.search(bookSearchCriteria);
	}
	
	@GET
	@Path("/books/{isbn}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("isbn") String isbn) {
		if (LOG.isLoggable(Level.FINEST)) {
			LOG.finest("retrieving isbn: "+isbn);
		}
		
		BookSearchCriteria criteria = new BookSearchCriteria();
		criteria.setIsbn(isbn);
		Book book = libraryDao.searchOne(criteria);
		return book;
	}
	
	@GET
	@Path("/books/{author}/{title}")
	@Produces({"application/json", "application/xml"})
	public Book getBook(@PathParam("author") String author, @PathParam("title") String title) {
		if (LOG.isLoggable(Level.INFO)) {
			LOG.info("author: " + author+"\ttitle: "+title);
		}
		BookSearchCriteria criteria = new BookSearchCriteria();
		criteria.setAuthor(author);
		criteria.setTitle(title);
		return libraryDao.searchOne(criteria);
	}
	
	@PUT
	@Path("/books")
	@Produces({"application/json", "application/xml"})
	public Book addBook(Book book) throws ServiceException {
		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine("New book: " + book);
		}
		
		Collection<ErrorCodeType> errors = new ArrayList<>();
		if (null == book.getIsbn() || book.getIsbn().isEmpty()) {
			errors.add(ErrorCodeType.MANDATORY_ISBN);
		}
		if (null == book.getAuthor() || book.getAuthor().isEmpty()) {
			errors.add(ErrorCodeType.MANDATORY_AUTHOR);
		}
		
		if (!errors.isEmpty()) {
			throw new ServiceException(errors);
		}
		
		return libraryDao.addBook(book);
	}
	
	@DELETE
	@Path("/books/{isbn}")
	@Produces({"application/json", "application/xml"})
	public void deleteBook(@PathParam("isbn") String isbn) {
		if (LOG.isLoggable(Level.INFO)) {
			LOG.info("Delete book: " + isbn);
		}
		libraryDao.delete(isbn);
	}
}
