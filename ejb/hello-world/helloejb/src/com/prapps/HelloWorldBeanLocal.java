package com.prapps;

import javax.ejb.Local;

@Local
public interface HelloWorldBeanLocal {

	String sayHelloLocal();
}
