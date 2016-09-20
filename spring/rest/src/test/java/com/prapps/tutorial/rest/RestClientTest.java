package com.prapps.tutorial.rest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.prapps.tutorial.rest.dto.Book;

/*@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)*/
public class RestClientTest {
	private static final Logger LOG = Logger.getLogger(RestClientTest.class.getName());
	private static final String url = "http://localhost:8080/rest/library/books";
	
	//@Autowired
	private TestRestTemplate restTemplate;
	
	RestTemplate restTemplate2 = new RestTemplate();
	
	@Test
	public void testGetBooks2() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		Book[] books = restTemplate2.getForEntity(url, Book[].class).getBody();
		System.out.println(books[0].getName());
	}
	
	@Test
	public void testGetBooks() {
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Accept", MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<String>(headers);
		ResponseEntity<Book[]> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Book[].class);
		LOG.info(""+response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		Book book = response.getBody()[0];
		LOG.info(book+"");
		assertEquals("My Gita", book.getName());
	}
}
