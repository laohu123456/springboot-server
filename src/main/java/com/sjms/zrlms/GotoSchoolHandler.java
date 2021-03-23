package com.sjms.zrlms;


/**
 * 责任链模式
 *  老师检查分数，教导主任检查品格，校长最后审批（前两级过了校长自然通过）
 */

public abstract class GotoSchoolHandler {

    protected GotoSchoolHandler handler;

    public void setHandler(GotoSchoolHandler handler){
        this.handler = handler;
    }

    public abstract void checkStudent(Student student);

}
