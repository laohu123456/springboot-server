package com.sjms.zrlms;

public class Test {

    public static void main(String[] args) {
        GotoSchoolHandler teacher = new Teacher();
        GotoSchoolHandler jdTeacher = new JdTeacher();
        GotoSchoolHandler headMaster = new HeadMaster();

        Student student = new Student();
        student.setName("a");
        student.setGrade(59);
        student.setCharster(CharsterConstant.getGoodCharster());

        teacher.setHandler(jdTeacher);
        jdTeacher.setHandler(headMaster);

        teacher.checkStudent(student);
    }

}
