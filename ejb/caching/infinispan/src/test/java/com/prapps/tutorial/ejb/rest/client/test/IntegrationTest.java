package com.prapps.tutorial.ejb.rest.client.test;

import java.net.URL;
import java.util.Calendar;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prapps.tutorial.ejb.rest.model.Book;
import com.prapps.tutorial.ejb.rest.service.LibraryService;

@RunWith(Arquillian.class)
public class IntegrationTest {

	private static final String APP_NAME = "infispan-caching";

	@ArquillianResource
	private URL deploymentUrl;
	
	@Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, APP_NAME + ".war").addPackage("com.prapps.tutorial.ejb");
    }
	
	@Test
	public void testAddBook(@ArquillianResteasyResource LibraryService service) {
		Book book = new Book();
		book.setAuthor("Herbert Schildt");
		book.setIsbn("0071823506, 9780071823500");
		book.setPublishedDate(Calendar.getInstance());
		book.setTitle("Java: The Complete Reference, Ninth Edition");
		Book addedBook = service.addBook(book);
	}
}
