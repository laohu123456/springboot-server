package com.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.annotation.SkipTokenOauth;
import com.server.entity.ExceptionEntity;
import com.server.redis.servcie.RedisSetService;
import com.server.service.UserService;
import com.server.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Component
public class AllInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisSetService redisSetService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //防盗刷 - redis限流、JWT验证(JWT续租)、参数校验
        String requestURI = request.getRequestURI();
        boolean allow = allowVisit(requestURI);
        if (allow) {
            return true;
        }
        boolean ifAllowAccess = skipTokenOauth(handler);
        if(ifAllowAccess){
            return true;
        }
        String token = request.getHeader(JwtUtils.TOKEN_HEADER);
        ExceptionEntity exceptionEntity = userService.oauthUser(token);
        if(exceptionEntity.getStatus() == 400){
            printCode(response,exceptionEntity);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void printCode(HttpServletResponse response, ExceptionEntity exceptionEntity) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        writer.println(objectMapper.writeValueAsString(exceptionEntity));
    }

    private boolean allowVisit(String uri){
        return redisSetService.findExist(uri);
    }

    /**
     * 注解方式(要写在拦截器中，因为拦截器在前)
     * 标注 @SkipTokenOauth 可以直接跳过token认证
     */
    public boolean skipTokenOauth(Object handler){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        return method.isAnnotationPresent(SkipTokenOauth.class);
    }
}
