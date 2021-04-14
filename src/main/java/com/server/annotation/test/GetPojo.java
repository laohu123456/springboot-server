package com.server.annotation.test;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface GetPojo {

    Class<?> className() default NotUse.class;

}
