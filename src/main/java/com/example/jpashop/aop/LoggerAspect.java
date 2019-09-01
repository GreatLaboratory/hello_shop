package com.example.jpashop.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Around("execution(* com.example.jpashop..controller.*Controller.*(..)) "
            + "|| execution(* com.example.jpashop..service.*Service.*(..)) ")
    public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable{
        String type = "";
        String name = joinPoint.getSignature().getDeclaringTypeName();

        if(name.indexOf("Controller") > -1) {
            type = "Controller  \t:  ";
        } else if(name.indexOf("Service") > -1) {
            type = "Service     \t:  ";
        }

        log.debug(type + name + "." + joinPoint.getSignature().getName() + "()");

        return joinPoint.proceed();
    }
}
