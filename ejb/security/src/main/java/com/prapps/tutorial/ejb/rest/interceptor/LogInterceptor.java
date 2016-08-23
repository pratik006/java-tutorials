package com.prapps.tutorial.ejb.rest.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.jboss.logging.Logger;

public class LogInterceptor {

	private static final Logger LOG = Logger.getLogger(LogInterceptor.class);
	
	@AroundInvoke
    public Object aroundInvoke(final InvocationContext ctx) throws Exception {
		LOG.trace("Before: " + ctx.getMethod().getName());
		return ctx.proceed();
	}
}
