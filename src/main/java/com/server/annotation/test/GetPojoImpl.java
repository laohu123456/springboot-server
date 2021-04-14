package com.server.annotation.test;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class GetPojoImpl {

    @Pointcut("@annotation(com.server.annotation.test.GetPojo)")
    public void getPojoImpl(){}

    @Before("getPojoImpl()")
    public void before(JoinPoint joinPoint) throws IllegalAccessException, InstantiationException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        boolean name1 = method.isAnnotationPresent(GetPojo.class);
        if(name1){
            GetPojo annotation = method.getAnnotation(GetPojo.class);
            Object obj= annotation.className().newInstance();
            if(obj instanceof NotUse){
                System.out.println("notClass");
                return ;
            }
            System.out.println(obj.toString());
        }
    }

}
