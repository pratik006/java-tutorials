package com.prapps.tutorial.ejb.rest.interceptor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RestSecurityInterceptor implements ContainerRequestFilter {

	private static final Logger LOG = Logger.getLogger(RestSecurityInterceptor.class.getName());
	
	public void filter(ContainerRequestContext ctx) throws IOException {
		if (LOG.isLoggable(Level.INFO)) {
			LOG.info("invoked RestSecurityInterceptor: "+ctx.getRequest());
		}
	}
}
