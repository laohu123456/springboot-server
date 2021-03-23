package com.sjms.zrlms;

public class HeadMaster extends GotoSchoolHandler{
    @Override
    public void checkStudent(Student student) {
        System.out.println("校长审批通过了" + student.getName() + " 可以入学了!");
        return;
    }
}
