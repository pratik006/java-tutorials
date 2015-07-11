package com.prapps.hello.ejb;

import javax.ejb.Remote;

@Remote
public interface HelloWorldBeanRemote {

	String sayHelloRemote();
	HelloResponse sayHelloRemoteDetail(HelloRequest helloRequest);
}
