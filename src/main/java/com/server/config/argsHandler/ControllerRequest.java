package com.server.config.argsHandler;

import com.server.entity.HandlerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 对Controller请求值 进行处理
 * 需要配合@RequestBody使用
 */

@ControllerAdvice
public class ControllerRequest implements RequestBodyAdvice {

    @Autowired
    private HandlerUtils handlerUtils;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return handlerUtils.needHandler(methodParameter, HandlerArgs.REQUEST);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("beforeBodyRead");
        System.out.println("request: " + inputMessage);
        System.out.println("request: " + parameter);
        System.out.println("request: " + targetType.getTypeName());

        Class<?> aClass = null;
        try {
            aClass = Class.forName(targetType.getTypeName());
            System.out.println("aaaaaa" + aClass.isAnnotationPresent(NeedArgsHandlerAll.class));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("request: " + converterType);
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("afterBodyRead");
        System.out.println("request: " + body);
        System.out.println("request: " + inputMessage);
        System.out.println("request: " + parameter);
        System.out.println("request: " + targetType);
        System.out.println("request: " + converterType);
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("handleEmptyBody");
        System.out.println("request: " + body);
        System.out.println("request: " + inputMessage);
        System.out.println("request: " + parameter);
        System.out.println("request: " + targetType);
        System.out.println("request: " + converterType);
        return body;
    }
}
