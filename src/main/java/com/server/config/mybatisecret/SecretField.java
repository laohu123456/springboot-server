package com.server.config.mybatisecret;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface SecretField {

    boolean excuet() default true;
}
