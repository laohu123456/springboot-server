package com.sjms.gczms;

import java.util.Observable;

public class ClassRoom extends Observable {

    private String classRoomName;

    public ClassRoom(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public void providerQuestion(Question question){
        System.out.println("在课堂上:" + question.getProviderName() + "提出了问题" + question.getProviderQuestion());
        setChanged();
        notifyObservers(question);
    }

}
