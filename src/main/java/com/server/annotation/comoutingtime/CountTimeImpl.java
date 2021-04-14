package com.server.annotation.comoutingtime;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CountTimeImpl {

    private Long start = 0L;

    /*
        Pointcut 逻辑判断
     */

    @Pointcut("@annotation(com.server.annotation.comoutingtime.CountTime) " +
            "|| @within(com.server.annotation.comoutingtime.CountTime)")
    public void countTimeImpl(){}

    @Before("countTimeImpl()")
    public void before(JoinPoint joinPoint) throws Exception {
        start = System.currentTimeMillis();
    }

    @After("countTimeImpl()")
    public void after(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String category = "";
        Method mehtod = methodSignature.getMethod();
        if(mehtod.isAnnotationPresent(CountTime.class)){
            CountTime annotation = mehtod.getAnnotation(CountTime.class);
            category = annotation.category();
        }else{
            Class type = methodSignature.getDeclaringType();  // OtherController.class
            CountTime annotation = (CountTime) type.getAnnotation(CountTime.class);
            category = annotation.category();
        }
        String methodName = mehtod.getName();
        long end = System.currentTimeMillis();
        System.out.println("本次访问 " + category + " method: " + methodName + " 耗时: " + (end - start) + " ms " + (end - start) / 1000 + " s");
    }
    
    


}
