package com.prapps.ejb.client;

import java.net.MalformedURLException;

import com.prapps.generated.HelloRequest;
import com.prapps.generated.HelloResponse;
import com.prapps.generated.HelloService;

/**
 * Hello world!
 *
 */
public class HelloWebServiceClientTest 
{
    public static void main( String[] args ) throws MalformedURLException
    {
		testWebService();
    }
    
    private static void testWebService() throws MalformedURLException {
    	HelloService service = new HelloService();
    	System.out.println(service.getHelloPort().sayHello());
    	HelloRequest request = new HelloRequest();
    	request.setId(1L);
    	request.setKey("test");
    	
    	HelloResponse response = service.getHelloPort().sayHelloDetail(request);
    	System.out.println(response.getResp());
    }
}
