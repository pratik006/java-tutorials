package com.prapps.example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class LoggingAspect {

    private Logger LOG = LoggerFactory.getLogger(LoggingAspect.class.getName());

    @Pointcut("execution(* com.prapps.example.PersonService.*(..))")
    public void serviceMethods() {};

    @Around("serviceMethods()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.nanoTime();
        Object retval = pjp.proceed();
        long end = System.nanoTime();
        String methodName = pjp.getSignature().getName();
        if (LOG.isTraceEnabled()) {
            LOG.trace("Execution of " + methodName + " took " + TimeUnit.NANOSECONDS.toMillis(end - start) + " ms");
        }
        return retval;
    }
}