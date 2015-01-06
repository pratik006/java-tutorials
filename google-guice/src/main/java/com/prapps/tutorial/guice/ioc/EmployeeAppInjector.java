package com.prapps.tutorial.guice.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.prapps.tutorial.guice.aop.LogAspect;
import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.service.impl.EmployeeServiceImpl;

public class EmployeeAppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(EmployeeService.class).to(EmployeeServiceImpl.class).in(Singleton.class);
		LogAspect logAspect = new LogAspect();
		requestInjection(logAspect);
		bindInterceptor(Matchers.subclassesOf(EmployeeService.class), Matchers.any(), logAspect);
	}
}
