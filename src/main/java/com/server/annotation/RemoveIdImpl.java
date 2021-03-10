package com.server.annotation;

import com.server.config.LocalSession;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RemoveIdImpl {

    @Pointcut("@annotation(com.server.annotation.RemoveUserId)")
    public void RemoveIdImpl() {
    }

    /**
     * 方法结束后执行，遇到方法异常也可以执行
     * 这个注解最好放到Controller上，controller方法执行结束，代表请求结束
     * 如有特殊需求，需要单独手动释放
     */
    @After("RemoveIdImpl()")
    public void after() {
        LocalSession.removeId();
    }
}
