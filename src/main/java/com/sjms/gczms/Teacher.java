package com.sjms.gczms;

import java.util.Observable;
import java.util.Observer;

public class Teacher implements Observer {

    private String name;

    public Teacher() {
    }

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    public void update(Observable o, Object arg) {
        ClassRoom classRoom = (ClassRoom) o;
        Question question = (Question) arg;
        System.out.println(this.name + "老师看到在" + classRoom.getClassRoomName() + "课堂上" + question.getProviderName() + "提出了" + question.getProviderQuestion());
    }
}
