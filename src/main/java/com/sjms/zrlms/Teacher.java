package com.sjms.zrlms;

public class Teacher extends GotoSchoolHandler{
    @Override
    public void checkStudent(Student student) {
        if(student.getGrade() < 60){
            System.out.println(student.getName() + "成绩不可以");
            return;
        }else{
            System.out.println(student.getName() + "成绩可以");
            if(handler != null){
                handler.checkStudent(student);
            }
        }
    }
}
