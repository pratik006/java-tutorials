package com.prapps.tutorial.ejb.rest.client.test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.prapps.tutorial.ejb.rest.Book;



public class LibraryServiceClientTest {

	private static final String url = "http://localhost:8080/restful-webservice/library";
	
	@Test
	public void testJsonOutput() {
		Client client = ClientBuilder.newClient();
		 WebTarget target = client.target(url + "/books");
		 Response response = target.request().get();
		 client.register(new AddAuthHeadersRequestFilter("root", "DefaultPasswordsAre:-("));
		 System.out.println(response.getHeaders());
		 System.out.println(response.getLength());
		   System.out.println("Server response : \n");
		   System.out.println(response.readEntity(String.class));
		 List<Book> books = (List<Book>) response.readEntity(Book.class);
		 Book book = books.get(0);
		 Assert.assertEquals(book.getAuthor(), "Devdutt Patnaik");
	}
	
	public static class AddAuthHeadersRequestFilter implements ClientRequestFilter {

	    private final String username;
	    private final String password;

	    public AddAuthHeadersRequestFilter(String username, String password) {
	        this.username = username;
	        this.password = password;
	    }

	    @Override
	    public void filter(ClientRequestContext requestContext) throws IOException {
	        String token = username + ":" + password;
	        String base64Token = Base64.encodeBase64String(token.getBytes(StandardCharsets.UTF_8));
	        //requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
	        requestContext.getHeaders().add("X-Requested-With", "XMLHttpRequest");
	    }
	}
}
