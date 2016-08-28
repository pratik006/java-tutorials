package com.prapps.tutorial.ejb.rest.interceptor;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptor {

	private static final Logger LOG = Logger.getLogger(LogInterceptor.class.getName());
	
	@AroundInvoke
    public Object aroundInvoke(final InvocationContext ctx) throws Exception {
		LOG.fine("Before: " + ctx.getMethod().getName());
		return ctx.proceed();
	}
}
