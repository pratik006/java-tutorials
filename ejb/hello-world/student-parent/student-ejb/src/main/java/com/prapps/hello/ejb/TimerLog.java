package com.prapps.hello.ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class TimerLog {
	
	/**
	   * Measure and print the time take for any method call
	   *
	   * @param ctx
	   *            The invocation context - contains the class info we're interested in.
	   * @return The result of the wrapped method call.
	   */
	  @AroundInvoke
	  public Object measure(InvocationContext ctx) throws Exception {
	    String beanClassName = ctx.getClass().getSimpleName();
	    String businessMethodName = ctx.getMethod().getName();
	    String target = beanClassName + "." + businessMethodName;
	    long startTime = System.currentTimeMillis();
	    System.out.println("Invoking " + target);
	    try {
	      return ctx.proceed();
	    } finally {
	      System.out.println("Exiting " + target);
	      long totalTime = System.currentTimeMillis() - startTime;
	      System.out.println("Business method " + businessMethodName + "in " + beanClassName
	          + "takes " + totalTime + "ms to execute");
	    }
	  }
}
