package com.server.config.argsHandler;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;

@Component
public class HandlerUtils {

    //先判断方式是否标注 在判断是否需要
    public boolean needHandler(MethodParameter methodParameter, HandlerArgs handlerArgs){
        boolean annotationPresent = methodParameter.getMethod().isAnnotationPresent(NeedArgsHandler.class);
        if(annotationPresent){
            NeedArgsHandler annotation = methodParameter.getMethod().getAnnotation(NeedArgsHandler.class);
            if(handlerArgs.equals(HandlerArgs.REQUEST)){
                return annotation.requestNeed();
            }else{
                return annotation.responseNeed();
            }
        }else{
            return false;
        }
    }

}
