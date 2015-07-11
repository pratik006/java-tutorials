package com.prapps.hello.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@Remote(HelloWorldBeanRemote.class)
@WebService(name="Hello", serviceName="HelloService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
@Interceptors(TimerLog.class)
public class HelloWorld implements HelloWorldBeanRemote {

    /**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

	@Override
	@WebMethod
	public String sayHelloRemote() {
		System.out.println("sayHelloRemote..");
		return "Hello World !!!";
	}

	@Override
	@WebMethod
	public HelloResponse sayHelloRemoteDetail(HelloRequest helloRequest) {
		HelloResponse helloResponse = new HelloResponse();
		helloResponse.setId(helloRequest.getId()+100);
		helloResponse.setResp("hello "+helloRequest.getKey());
		return helloResponse;
	}
}
