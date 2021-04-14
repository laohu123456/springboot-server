package com.server.annotation.comoutingtime;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@CountTime
public @interface AllType {

}
