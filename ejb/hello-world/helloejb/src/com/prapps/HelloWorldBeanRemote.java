package com.prapps;

import javax.ejb.Remote;

@Remote
public interface HelloWorldBeanRemote {

	String sayHelloRemote();
}
