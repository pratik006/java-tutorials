package com.prapps.ejb.client;

import java.net.MalformedURLException;

import com.prapps.generated.HelloRequest;
import com.prapps.generated.HelloResponse;
import com.prapps.generated.HelloService;
import com.prapps.generated.Student;
import com.prapps.generated.StudentSearchRequest;
import com.prapps.generated.StudentSearchResponse;

/**
 * Hello world!
 *
 */
public class HelloWebServiceClientTest 
{
    public static void main( String[] args ) throws MalformedURLException
    {
    	int ctr = 1000;
    	while(ctr-- > 0) {
    		testWebService();
    	}
    }
    
    private static void testWebService() throws MalformedURLException {
    	HelloService service = new HelloService();
    	System.out.println(service.getHelloPort().sayHello());
    	HelloRequest request = new HelloRequest();
    	request.setId(1L);
    	request.setKey("test");
    	
    	HelloResponse response = service.getHelloPort().sayHelloDetail(request);
    	System.out.println(response.getResp());
    	
    	StudentSearchRequest studentSearchRequest = new StudentSearchRequest();
    	StudentSearchResponse stdSearchResponse =  service.getHelloPort().getStudents(studentSearchRequest);
    	System.out.println("Student Details...\n-------------------------");
    	for (Student student : stdSearchResponse.getStudents()) {
    		System.out.println(student.getId()+"\t"+student.getFirstName()+"\t"+student.getLastName());
    	}
    }
}
