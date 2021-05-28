package com.sjms.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {


    private Object obj;

    public Proxy(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        eat(args);
        result = method.invoke(obj,args);
        return result;
    }

    public void eat(Object[] args){
        System.out.println(getStr(args) + ": 开始吃饭");
    }

    public String getStr(Object[] args){
        StringBuffer sbf = new StringBuffer();
        if(args != null){
            for (int i = 0; i < args.length; i++) {
                sbf.append(args[i]);
            }
        }
        return sbf.toString();
    }
}
