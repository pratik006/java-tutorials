package com.prapps.tutorial.ejb.rest.client.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.util.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.prapps.tutorial.ejb.rest.model.Book;

public class LibraryServiceClientTest {
	private static final Logger LOG = Logger.getLogger(LibraryServiceClientTest.class.getName());
	private static final String url = "http://localhost:8080/restful-webservice/library/books";

	static {
		System.setProperty("java.util.logging.config.file", "logging.properties");
	}

	@Test
	public void testGetBooks() {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		WebTarget target = client.target(url);
		Response response = target.request().get();
		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		System.out.println(response.readEntity(String.class));
		GenericType<List<Book>> bookListType = new GenericType<List<Book>>() {
		};
		List<Book> books = response.readEntity(bookListType);
		Book book = books.get(0);
		Assert.assertEquals(book.getAuthor(), "Devdutt Patnaik");
		Assert.assertEquals(book.getIsbn(), "123asd");
		Assert.assertEquals(book.getTitle(), "My Gita");
	}

	@Test
	public void testGetBookByIsbn() {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		WebTarget target = client.target(url).path("/{isbn}").resolveTemplate("isbn", "123abc");
		Response response = target.request().get();
		Book book = response.readEntity(Book.class);

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		LOG.fine("Server response : \n");
		LOG.fine("Title: " + book.getTitle() + "\tAuthor: " + book.getAuthor() + "\tISBN: " + book.getIsbn());
		Assert.assertEquals("My Gita", book.getTitle());
		Assert.assertEquals("Devdutt Patnaik", book.getAuthor());
	}

	@Test
	public void testGetBookByAuthorAndTitle() {
		Client client = ClientBuilder.newClient().register(AddHeadersFilter.INSTANCE);
		Map<String, Object> map = new HashMap<>();
		map.put("author", "Devdutt Patnaik");
		map.put("title", "My Gita");
		WebTarget target = client.target(url).path("/{author}/{title}").resolveTemplates(map);
		Response response = target.request().get();
		Book book = response.readEntity(Book.class);

		LOG.fine("Headers" + response.getHeaders());
		LOG.fine("Status: " + response.getStatus());
		LOG.fine("Server response : \n");
		LOG.fine("Title: " + book.getTitle() + "\tAuthor: " + book.getAuthor() + "\tISBN: " + book.getIsbn());
		Assert.assertEquals("My Gita", book.getTitle());
		Assert.assertEquals("Devdutt Patnaik", book.getAuthor());
	}

	public enum AddHeadersFilter implements ClientRequestFilter {
		INSTANCE;

		private AddHeadersFilter() {
		}

		@Override
		public void filter(ClientRequestContext requestContext) throws IOException {
			String token = "testuser1:password1";
			String base64Token = Base64.encodeBytes(token.getBytes(StandardCharsets.UTF_8));
			requestContext.getHeaders().add("X-Requested-With", "XMLHttpRequest");
			requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
			requestContext.getHeaders().add("Accept", "application/xml");

		}
	}
}
