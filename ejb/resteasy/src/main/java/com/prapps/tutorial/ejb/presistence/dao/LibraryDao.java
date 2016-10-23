package com.prapps.tutorial.ejb.presistence.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.prapps.tutorial.ejb.persistence.BookEntity;
import com.prapps.tutorial.ejb.persistence.api.BookSearchCriteria;
import com.prapps.tutorial.ejb.persistence.mapper.BookMapper;
import com.prapps.tutorial.ejb.rest.model.Book;

@Singleton
public class LibraryDao {

	@Inject
	private BookMapper bookMapper;
	
	@PersistenceContext(name = "persistenceUnit")
	private EntityManager em;
	
	private EntityManager getEntityManager() {
		return em;
	}
	
	public Book searchOne(BookSearchCriteria bookSearchCriteria) {
		BookEntity entity = null;
		if (bookSearchCriteria.getIsbn() != null) {
			entity = getEntityManager().find(BookEntity.class, bookSearchCriteria.getIsbn());
		} else {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			// Query for a List of objects.
			CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
			Root<BookEntity> e = cq.from(BookEntity.class);
			if (bookSearchCriteria.getAuthor() != null) {
				cq.where(cb.equal(e.get("author"), bookSearchCriteria.getAuthor()));
			}
			if (bookSearchCriteria.getTitle() != null) {
				cq.where(cb.equal(e.get("title"), bookSearchCriteria.getTitle()));
			}
			
			Query query = getEntityManager().createQuery(cq);
			entity = (BookEntity) query.getSingleResult();
		}
		
		return bookMapper.map(entity);
	}
	
	public List<Book> search(BookSearchCriteria bookSearchCriteria) {
		List<BookEntity> entities = null;
		if (bookSearchCriteria.getIsbn() != null) {
			BookEntity entity = getEntityManager().find(BookEntity.class, bookSearchCriteria.getIsbn());
			entities = Collections.singletonList(entity);
		} else {
			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

			// Query for a List of objects.
			CriteriaQuery<BookEntity> cq = cb.createQuery(BookEntity.class);
			Root<BookEntity> e = cq.from(BookEntity.class);
			if (bookSearchCriteria.getAuthor() != null) {
				cq.where(cb.equal(e.get("author"), bookSearchCriteria.getAuthor()));
			}
			if (bookSearchCriteria.getTitle() != null) {
				cq.where(cb.equal(e.get("title"), bookSearchCriteria.getTitle()));
			}
			
			Query query = getEntityManager().createQuery(cq);
			entities = query.getResultList();
		}
		
		return bookMapper.map(entities);
	}
	
	public Book addBook(Book book) {
		BookEntity entity = bookMapper.map(book);
		entity = em.merge(entity);
		return bookMapper.map(entity);
	}
	
	public void delete(String isbn) {
		BookEntity entity = getEntityManager().find(BookEntity.class, isbn);
		em.remove(entity);
	}
}
