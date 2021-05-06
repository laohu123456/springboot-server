package com.sjms.builer;

public class Test {

    public static void main(String[] args) {
        Student student = new Student
                .StudentBuilder()
                .setAddress("天津")
                .setName("张三")
                .setAge(20)
                .build();
        System.out.println(student);
    }
}
