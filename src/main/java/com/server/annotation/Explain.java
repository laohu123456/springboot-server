package com.server.annotation;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Explain {


    /**
     * Aop 执行顺序
     *
     * 正常情况下
     * @Around --> @Before --> @Around --> @After --> @AfterReturning
     *
     * 异常情况下
     * @Around --> @Before --> @Around --> @After --> @AfterThrowing
     *
     */
}
