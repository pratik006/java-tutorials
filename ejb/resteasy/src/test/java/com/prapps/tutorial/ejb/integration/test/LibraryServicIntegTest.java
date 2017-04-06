package com.prapps.tutorial.ejb.integration.test;

import java.util.Calendar;
import java.util.Collection;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prapps.tutorial.ejb.persistence.mapper.BookMapper;
import com.prapps.tutorial.ejb.presistence.dao.LibraryDao;
import com.prapps.tutorial.ejb.rest.exception.ServiceException;
import com.prapps.tutorial.ejb.rest.model.Book;
import com.prapps.tutorial.ejb.rest.service.LibraryService;

@RunWith(Arquillian.class)
public class ServicTest {

	private static final String APP_NAME = "resteasy";

	/*@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, APP_NAME + ".war")
				
				.addPackages(true, LibraryService.class.getPackage())
				//.addPackages(true, BookMapper.class.getPackage())
				.addClass(BookMapper.class)
				.addClass(LibraryDao.class)
				.addPackages(true, "com.prapps")
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}*/
	
	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
    		.addPackages(true, LibraryService.class.getPackage())
			//.addPackages(true, BookMapper.class.getPackage())
			.addClass(BookMapper.class)
			.addClass(LibraryDao.class)
			.addPackages(true, "com.prapps")
			.addAsResource("META-INF/persistence.xml")
			.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@Inject	LibraryService service;

	@Test
	public void testService() throws ServiceException {
		Book book = new Book();
		book.setAuthor("aaa");
		book.setIsbn("asdasd324rew");
		book.setPublishedDate(Calendar.getInstance());
		book.setTitle("Test");
		Book added = service.addBook(book);
		Assert.assertNotNull(added);
		Collection<Book> books = service.getBooks();
		Assert.assertTrue(books.size() > 0);
	}
}
