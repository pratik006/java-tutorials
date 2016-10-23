package com.prapps.tutorial.ejb.rest.client.test;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.prapps.tutorial.ejb.rest.exception.type.ServiceErrorMessage;
import com.prapps.tutorial.ejb.rest.model.BaseResponse;
import com.prapps.tutorial.ejb.rest.model.Book;

public class LibraryServiceClientTest {
	private static final Logger LOG = Logger.getLogger(LibraryServiceClientTest.class.getName());
	private static final String url = "http://localhost:8080/rest/library/books";

	static {
		System.setProperty("java.util.logging.config.file", "src/test/resources/logging.properties");
	}
	
	private static final String ISBN = "9788129137708";
	private static final String AUTHOR = "Devdutt Patnaik";
	private static final String TITLE = "My Gita";
	
	private Book addBook(Book book) {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Entity<Book> entity = Entity.entity(book, MediaType.APPLICATION_JSON);
		LOG.finest("targetUrl: "+url);
		Response response = client.target(url).request().put(entity);

		LOG.finest("Headers" + response.getHeaders());
		LOG.finest("Status: " + response.getStatus());
		Book addedBook = response.readEntity(Book.class);
		LOG.finest("addedBook: "+addedBook);
		
		Book retrieved = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE)
				.target(url).path("/{isbn}").resolveTemplate("isbn", book.getIsbn()).request().get().readEntity(Book.class);
		return retrieved;
	}
	
	private boolean deleteBook(Book book) {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Response response = client.target(url+"/"+book.getIsbn()).request().delete();

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		
		Book retrieved = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE)
				.target(url).path("/{isbn}").resolveTemplate("isbn", book.getIsbn()).request().get().readEntity(Book.class);
		return retrieved==null;
	}
	
	private Book findBook(String isbn) {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Response response = client.target(url+"/"+isbn).request().get();
		if (response.getStatus() == 200 && response.hasEntity()) {
			return response.readEntity(Book.class);
		}
		
		return null;
	}
	
	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setAuthor("Herbert Schildt");
		book.setIsbn("0071823506, 9780071823500");
		book.setPublishedDate(Calendar.getInstance());
		book.setTitle("Java: The Complete Reference, Ninth Edition");
		Book retrieved = addBook(book);
		Assert.assertEquals(book, retrieved);
		
		Book searchedBook = findBook(book.getIsbn());
		Assert.assertEquals(book.getIsbn(), searchedBook.getIsbn());
		Assert.assertEquals(book.getAuthor(), searchedBook.getAuthor());
		
		//deleteBook(retrieved);
	}

	@Test
	public void testAddBookFailed() {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Book book = new Book();
		Entity<Book> entity = Entity.entity(book, MediaType.APPLICATION_JSON);
		Response response = client.target(url).request().put(entity);

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		BaseResponse baseResponse = response.readEntity(BaseResponse.class);
		Assert.assertEquals("failed", baseResponse.getStatus());
		Assert.assertTrue(!baseResponse.getErrors().isEmpty());
		for (ServiceErrorMessage errorMsg : baseResponse.getErrors()) {
			LOG.fine(errorMsg.getErrorCode()+" - "+errorMsg.getErrorCode());
		}
	}

	@Test
	public void testGetBookByIsbn() {
		Book newbook = new Book();
		newbook.setAuthor(AUTHOR);
		newbook.setIsbn(ISBN);
		newbook.setPublishedDate(Calendar.getInstance());
		newbook.setTitle(TITLE);
		Book addedBook = addBook(newbook);
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		WebTarget target = client.target(url).path("/{isbn}").resolveTemplate("isbn", newbook.getIsbn());
		Response response = target.request().get();
		Book searchedBook = response.readEntity(Book.class);

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		LOG.fine("Searched Book: "+searchedBook);
		Assert.assertEquals(AUTHOR, searchedBook.getAuthor());
		Assert.assertEquals(ISBN, searchedBook.getIsbn());
		Assert.assertEquals(TITLE, searchedBook.getTitle());
		
		deleteBook(addedBook);
	}
	
	@Test
	public void testGetBooks() {
		Book newbook = new Book();
		newbook.setAuthor(AUTHOR);
		newbook.setIsbn(ISBN);
		newbook.setPublishedDate(Calendar.getInstance());
		newbook.setTitle(TITLE);
		Book addedBook = addBook(newbook);
		
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		WebTarget target = client.target(url);
		Response response = target.request().get();
		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		GenericType<List<Book>> bookListType = new GenericType<List<Book>>() {};
		List<Book> books = response.readEntity(bookListType);
		Book book = books.get(0);
		Assert.assertEquals(AUTHOR, book.getAuthor());
		Assert.assertEquals(ISBN, book.getIsbn());
		Assert.assertEquals(TITLE, book.getTitle());
		
		deleteBook(addedBook);
	}

	@Test
	public void testGetBookByAuthorAndTitle() {
		Book newbook = new Book();
		newbook.setAuthor(AUTHOR);
		newbook.setIsbn(ISBN);
		newbook.setPublishedDate(Calendar.getInstance());
		newbook.setTitle(TITLE);
		Book addedBook = addBook(newbook);
		
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Map<String, Object> map = new HashMap<>();
		map.put("author", AUTHOR);
		map.put("title", TITLE);
		WebTarget target = client.target(url).path("/{author}/{title}").resolveTemplates(map);
		Response response = target.request().get();
		Book book = response.readEntity(Book.class);

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		LOG.fine("Title: " + book.getTitle() + "\tAuthor: " + book.getAuthor() + "\tISBN: " + book.getIsbn());
		Assert.assertEquals(TITLE, book.getTitle());
		Assert.assertEquals(AUTHOR, book.getAuthor());
		
		deleteBook(addedBook);
	}

	public enum AddHeadersFilter implements ClientRequestFilter {
		INSTANCE;

		private AddHeadersFilter() {
		}

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			/*
			 * String token = username + ":" + password; String base64Token =
			 * Base64.encodeBase64String(token.getBytes(StandardCharsets.UTF_8))
			 * ;
			 */
			// requestContext.getHeaders().add("Authorization", "Basic " +
			// base64Token);
			// requestContext.getHeaders().add("X-Requested-With",
			// "XMLHttpRequest");
			requestContext.getHeaders().add("Accept", "application/json");

		}
	}
}
