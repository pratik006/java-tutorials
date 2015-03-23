package com.prapps.tutorial.webservice.jaxws.impl.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.prapps.tutorial.webservice.jaxws.impl.EmployeeService;

public class EmployeeServiceClient {

	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("http://localhost:9999/ws/hello?wsdl");
		QName qname = new QName("http://impl.jaxws.webservice.tutorial.prapps.com/",
				"EmployeeServiceImplService");

		Service service = Service.create(url, qname);
		EmployeeService hello = service.getPort(EmployeeService.class);
		System.out.println(hello.findPerson(101));
	}

}
