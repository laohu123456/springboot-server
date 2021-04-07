package com.server.config.argsHandler;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface NeedArgsHandlerAll {

    /**
     * 在实体类上标注代表实体每个属性都需要
     * @return
     */
    String[] ignoreFiled() default "";

    boolean request() default false;

    boolean response() default false;
}
