package com.sjms.proxy;

public class Student implements GoToSchool{

    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public void study(String name) {
        System.out.println(this.name +  "开始学习");
    }

}
