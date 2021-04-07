package com.server.config.argsHandler;

import com.server.entity.HandlerArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对Controller响应值 进行处理
 * 需要配合@ResponseBody注解使用
 */

@ControllerAdvice
public class ControllerResponse implements ResponseBodyAdvice {

    @Autowired
    private HandlerUtils handlerUtils;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return handlerUtils.needHandler(returnType, HandlerArgs.RESPONSE);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        System.out.println("response: " + body);
        System.out.println("response: " + returnType);
        System.out.println("response: " + selectedContentType);
        System.out.println("response: " + selectedConverterType);
        System.out.println("response: " + request);
        System.out.println("response: " + response);
        return body;
    }
}
