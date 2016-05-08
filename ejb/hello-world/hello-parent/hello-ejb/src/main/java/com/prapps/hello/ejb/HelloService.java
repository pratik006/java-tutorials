package com.prapps.hello.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.prapps.generated.Hello;
import com.prapps.generated.StudentSearchRequest;
import com.prapps.generated.StudentSearchResponse;
import com.prapps.hello.ejb.mapper.student.StudentMapper;
import com.prapps.hello.persistence.dao.StudentDao;
import com.prapps.student.api.Student;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@Remote(HelloWorldBeanRemote.class)
@WebService(name="Hello", serviceName="HelloService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
@Interceptors(TimerLog.class)
public class HelloService implements HelloWorldBeanRemote, Hello {

	@Inject private StudentDao studentDao;
	@Inject StudentMapper studentMapper;
	
	@Override
	public String sayHelloRemote() {
		System.out.println("sayHelloRemote..");
		return "Hello World !!!";
	}

	@Override
	public HelloResponse sayHelloRemoteDetail(HelloRequest helloRequest) {
		HelloResponse helloResponse = new HelloResponse();
		helloResponse.setId(helloRequest.getId()+100);
		helloResponse.setResp("hello "+helloRequest.getKey());
		return helloResponse;
	}
	
	@Override
	@WebMethod
	public String sayHello() {
		return sayHelloRemote();
	}

	@Override
	public com.prapps.generated.HelloResponse sayHelloDetail(com.prapps.generated.HelloRequest sayHelloDetail) {
		com.prapps.generated.HelloResponse helloResponse = new com.prapps.generated.HelloResponse();
		helloResponse.setId(sayHelloDetail.getId()+100);
		helloResponse.setResp("hello "+sayHelloDetail.getKey());
		
		return helloResponse;
	}
	
	@Override
	public StudentSearchResponse getStudents(StudentSearchRequest studentSearchRequest) {
		List<Student> list = studentDao.getStudents();
		StudentSearchResponse response = new StudentSearchResponse();
		for (Student student : list) {
			response.getStudents().add(studentMapper.mapStudent(student));	
		}
		return response;
	}
	
	@Override
	public List<Student> searchStudents() {
		List<Student> list = studentDao.getStudents();
		return list;
	}
}
