package com.server.config.argsHandler;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NeedArgsHandler {

    /*
       是否需要对标注了注解的方法进行处理
        requestNeed 是进行request参数进行处理
        responseNeed 是对response参数进行处理
     */
    boolean requestNeed() default true;

    boolean responseNeed() default true;


}
