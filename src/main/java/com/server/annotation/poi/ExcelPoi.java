package com.server.annotation.poi;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelPoi {

    String name() default "";

    boolean require() default false;

    int order();

    boolean combination() default false;


}
