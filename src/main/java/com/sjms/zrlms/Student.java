package com.sjms.zrlms;



public class Student {

    private String name;
    private Integer grade; //成绩
    private String Charster;// 品格


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getCharster() {
        return Charster;
    }

    public void setCharster(String charster) {
        Charster = charster;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                ", Charster='" + Charster + '\'' +
                '}';
    }



}
