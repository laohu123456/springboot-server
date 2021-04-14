package com.server.annotation.comoutingtime;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CountTime {

     String category() default Category.CONTROLLER;


}
