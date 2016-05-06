package com.prapps.hello.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.prapps.generated.Hello;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@Remote(HelloWorldBeanRemote.class)
@WebService(name="Hello", serviceName="HelloService")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
@Interceptors(TimerLog.class)
public class HelloService implements HelloWorldBeanRemote, Hello {

    /**
     * Default constructor. 
     */
    public HelloService() {
        // TODO Auto-generated constructor stub
    }

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
		return sayHelloDetail(sayHelloDetail);
	}
}
