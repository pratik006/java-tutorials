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
		headers.add("Authorization", "bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJQVVd6M0JLOHFtTENoeURaR1cwZmcwWDdIWmZ4NGZsSkFhNGhwQmJqbkk0In0.eyJqdGkiOiJkYTQ5MjhkYS1mY2QxLTRlMGMtOWQ1ZC0zZjIwYzI3NGEzOWYiLCJleHAiOjE0NzQ0MDI0MTksIm5iZiI6MCwiaWF0IjoxNDc0Mzk4ODE5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWRlbW8iLCJhdWQiOiJjdXJsIiwic3ViIjoiMDFmOGNlOGUtNzQ0Zi00YjU0LWEzYzAtMTY4YmMwMGU4MmQ5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY3VybCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjU4YWQyODFhLWY5Y2MtNGQ4OS1hM2FkLTQwNjRiM2QzMjUyMyIsImFjciI6IjEiLCJjbGllbnRfc2Vzc2lvbiI6IjRlOGJhNWM2LTMxNTEtNGQ2YS1iODcxLTBmZjI0ZTJiY2YxZiIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX1VTRVIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IlNjb3R0IFJvc3NpbGxvIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3Jvc3NpbGxvIiwiZ2l2ZW5fbmFtZSI6IlNjb3R0IiwiZmFtaWx5X25hbWUiOiJSb3NzaWxsbyIsImVtYWlsIjoic3Jvc3NpbGxvQHNtYXJ0bGluZy5jb20ifQ.J0gckTWwLuH0nDoVYqUDpAo9GoQuYc_DkywTaJNWkYMcMRsQmpRLs6efah6i4USl6iSvjlq7Ds5SgzdxYV-A0rIXhsqyclPXJHgipm0cj3p_4Cbce-wve7Ufyybsu_zPhe0e3moTFMlaGwDZkr_3WGfsqaX9jF5hcRqlsBGfBX4");
		HttpEntity<String> request = new HttpEntity<String>("parameters", headers);
		Book[] books = restTemplate2.exchange(url, HttpMethod.GET, request, Book[].class).getBody();
		System.out.println(books[0].getName());
	}
	
	@Test
	public void testGetBooks() {
		HttpHeaders headers = new HttpHeaders();
		//headers.add("Authorization", "bearer " + "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJQVVd6M0JLOHFtTENoeURaR1cwZmcwWDdIWmZ4NGZsSkFhNGhwQmJqbkk0In0.eyJqdGkiOiJkYTQ5MjhkYS1mY2QxLTRlMGMtOWQ1ZC0zZjIwYzI3NGEzOWYiLCJleHAiOjE0NzQ0MDI0MTksIm5iZiI6MCwiaWF0IjoxNDc0Mzk4ODE5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvc3ByaW5nLWRlbW8iLCJhdWQiOiJjdXJsIiwic3ViIjoiMDFmOGNlOGUtNzQ0Zi00YjU0LWEzYzAtMTY4YmMwMGU4MmQ5IiwidHlwIjoiQmVhcmVyIiwiYXpwIjoiY3VybCIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjU4YWQyODFhLWY5Y2MtNGQ4OS1hM2FkLTQwNjRiM2QzMjUyMyIsImFjciI6IjEiLCJjbGllbnRfc2Vzc2lvbiI6IjRlOGJhNWM2LTMxNTEtNGQ2YS1iODcxLTBmZjI0ZTJiY2YxZiIsImFsbG93ZWQtb3JpZ2lucyI6W10sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJST0xFX1VTRVIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1wcm9maWxlIl19fSwibmFtZSI6IlNjb3R0IFJvc3NpbGxvIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3Jvc3NpbGxvIiwiZ2l2ZW5fbmFtZSI6IlNjb3R0IiwiZmFtaWx5X25hbWUiOiJSb3NzaWxsbyIsImVtYWlsIjoic3Jvc3NpbGxvQHNtYXJ0bGluZy5jb20ifQ.J0gckTWwLuH0nDoVYqUDpAo9GoQuYc_DkywTaJNWkYMcMRsQmpRLs6efah6i4USl6iSvjlq7Ds5SgzdxYV-A0rIXhsqyclPXJHgipm0cj3p_4Cbce-wve7Ufyybsu_zPhe0e3moTFMlaGwDZkr_3WGfsqaX9jF5hcRqlsBGfBX4");
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
