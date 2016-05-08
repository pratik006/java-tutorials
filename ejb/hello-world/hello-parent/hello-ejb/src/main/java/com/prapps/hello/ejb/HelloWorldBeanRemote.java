package com.prapps.hello.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.prapps.student.api.Student;

@Remote
public interface HelloWorldBeanRemote {

	String sayHelloRemote();
	HelloResponse sayHelloRemoteDetail(HelloRequest helloRequest);
	List<Student> searchStudents();
	
}
