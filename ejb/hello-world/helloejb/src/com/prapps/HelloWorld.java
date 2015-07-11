package com.prapps;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@Remote(HelloWorldBeanRemote.class)
public class HelloWorld implements HelloWorldBeanRemote, HelloWorldBeanLocal {

    /**
     * Default constructor. 
     */
    public HelloWorld() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public String sayHelloLocal() {
		System.out.println("sayHelloRemoteLocal..");
		return "Hello World Local !!!";
	}

	@Override
	public String sayHelloRemote() {
		System.out.println("sayHelloRemote..");
		return "Hello World !!!";
	}

}
