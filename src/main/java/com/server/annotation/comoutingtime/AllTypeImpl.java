package com.server.annotation.comoutingtime;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AllTypeImpl {

    /**
     * ElementType.TYPE 类 @winthin生效  @target
     * @param joinPoint
     */

    @After("@within(com.server.annotation.comoutingtime.AllType)")
    public void after(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("allTpye:" + method.getName());
    }

}
