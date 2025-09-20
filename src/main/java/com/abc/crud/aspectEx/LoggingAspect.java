package com.abc.crud.aspectEx;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Apply before methods in MyService
    @Before("execution(* com.example.demo.MyService.*(..))")
    public void logBefore() {
        System.out.println(">>> Logging before method execution");
    }
}
