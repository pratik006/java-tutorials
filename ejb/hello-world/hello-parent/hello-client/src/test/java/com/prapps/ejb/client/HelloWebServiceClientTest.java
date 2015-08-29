package com.prapps.ejb.client;

import java.net.MalformedURLException;

import javax.xml.ws.Holder;

import com.prapps.hello.ejb.stub.HelloService;

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
    	System.out.println(service.getHelloPort().sayHelloRemote());
    	Holder<Long> holder = new Holder<Long>(123l);
    	Holder<String> holder2 = new Holder<String>();
    	service.getHelloPort().sayHelloRemoteDetail(holder, "World", holder2);
    	System.out.println(holder2.value);
    }
}
