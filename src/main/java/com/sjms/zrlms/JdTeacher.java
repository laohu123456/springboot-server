package com.sjms.zrlms;

public class JdTeacher extends GotoSchoolHandler{
    @Override
    public void checkStudent(Student student) {
        if(CharsterConstant.getGoodCharster().equals(student.getCharster())){
            System.out.println(student.getName() + "成绩可以,性格也可以");
            if(handler != null){
                handler.checkStudent(student);
            }
        }else{
            System.out.println(student.getName() + "成绩可以,性格差点");
            return;
        }
    }
}
