package com.prapps.tutorial.webservice.jaxws.api;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.prapps.tutorial.webservice.jaxws.vo.Employee;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL)
public interface EmployeeService {

	@WebMethod
	Employee findPerson(long id);
}
