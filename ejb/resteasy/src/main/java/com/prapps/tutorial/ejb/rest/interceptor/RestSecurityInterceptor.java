package com.prapps.tutorial.ejb.rest.interceptor;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class RestSecurityInterceptor implements ContainerRequestFilter {

	private static final Logger LOG = Logger.getLogger(RestSecurityInterceptor.class);
	
	public void filter(ContainerRequestContext ctx) throws IOException {
		if (LOG.isTraceEnabled()) {
			LOG.trace("invoked RestSecurityInterceptor: "+ctx.getRequest());
		}
	}
}
