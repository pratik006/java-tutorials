package com.prapps.hello.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;

public class Database {

	private @PersistenceContext(unitName="hibernateUnit") EntityManager factory;
	
	public Session getSession() {
		return (Session) factory.getDelegate();
	}
 
}
