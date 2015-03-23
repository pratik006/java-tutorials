package com.prapps.tutorial.webservice.jaxws.publisher;

import javax.xml.ws.Endpoint;

import com.prapps.tutorial.webservice.jaxws.impl.EmployeeServiceImpl;

public class EmployeeServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:9999/ws/hello", new EmployeeServiceImpl());
	}
}
