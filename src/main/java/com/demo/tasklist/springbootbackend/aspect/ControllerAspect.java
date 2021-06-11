package com.demo.tasklist.springbootbackend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(* com.demo.tasklist.springbootbackend.controller.*.*(..))")
    private void beforeControllerMethodsPointcut() {

    }

    @Before("beforeControllerMethodsPointcut()")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println(signature.getMethod().getDeclaringClass()+":"+signature.getName()+" -------------------------------------------------");

    }

}
