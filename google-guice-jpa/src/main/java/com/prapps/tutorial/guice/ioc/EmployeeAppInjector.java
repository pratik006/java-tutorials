package com.prapps.tutorial.guice.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.prapps.tutorial.guice.aop.LogAspect;
import com.prapps.tutorial.guice.dao.api.EmployeeDao;
import com.prapps.tutorial.guice.dao.impl.EmployeeDaoImpl;
import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.service.impl.EmployeeServiceImpl;

public class EmployeeAppInjector extends AbstractModule {

	@Override
	protected void configure() {
		install(new JpaPersistModule("employee-persister"));
		bind(EmployeeService.class).to(EmployeeServiceImpl.class).in(Singleton.class);
		bind(EmployeeDao.class).to(EmployeeDaoImpl.class).in(Singleton.class);
		LogAspect logAspect = new LogAspect();
		requestInjection(logAspect);
		bindInterceptor(Matchers.subclassesOf(EmployeeService.class), Matchers.any(), logAspect);
		bind(PersistenceInitializer.class);
	}
}
