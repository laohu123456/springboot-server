package com.server.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {


    @ExceptionHandler(value = {AllException.class})
    @ResponseBody
    public Map<String, Object> getExceptionResult(AllException allException){
        Map<String, Object> map = new HashMap<>();
        map.put("code", allException.getCode());
        map.put("message", allException.getMessage());
        return map;
    }

}
