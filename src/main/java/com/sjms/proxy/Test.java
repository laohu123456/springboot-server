package com.sjms.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    /**
     * newProxyInstance，方法有三个参数：
     *
     * loader: 用哪个类加载器去加载代理对象
     *
     * interfaces:动态代理类需要实现的接口
     *
     * h:动态代理方法在执行时，会调用h里面的invoke方法去执行
     * @param args
     */
    public static void main(String[] args) {
        String name = "张三";
        GoToSchool goToSchool = new Student(name);
        InvocationHandler invocationHandler = new com.sjms.proxy.Proxy(goToSchool);
        GoToSchool student = (GoToSchool) Proxy.newProxyInstance(
                goToSchool.getClass().getClassLoader(),
                goToSchool.getClass().getInterfaces(),
                invocationHandler);
        student.study(name);
    }

}
