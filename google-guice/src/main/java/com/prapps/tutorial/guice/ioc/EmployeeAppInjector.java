package com.prapps.tutorial.guice.ioc;

import com.google.inject.AbstractModule;
import com.prapps.tutorial.guice.service.api.EmployeeService;
import com.prapps.tutorial.guice.service.impl.EmployeeServiceImpl;

public class EmployeeAppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(EmployeeService.class).to(EmployeeServiceImpl.class);		
	}
}
