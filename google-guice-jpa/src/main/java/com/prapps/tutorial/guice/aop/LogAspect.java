package com.prapps.tutorial.guice.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LogAspect implements MethodInterceptor {
	
	private static final Logger LOG = Logger.getLogger(LogAspect.class);

	public Object invoke(MethodInvocation invocation) throws Throwable {
		LOG.trace(invocation.getMethod());
		if(null != invocation.getArguments() && invocation.getArguments().length > 0) {
			for(Object arg : invocation.getArguments()) {
				LOG.trace(arg.getClass() + " -- " + arg);
			}
		}
		Object response = invocation.proceed();
		LOG.trace(response);
		return response;
	}

	
}
