package com.prapps.tutorial.rest;

import javax.xml.ws.Endpoint;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(Endpoint.class);
		register(LibraryRestService.class);
	}

}