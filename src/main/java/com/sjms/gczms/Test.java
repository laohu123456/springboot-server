package com.sjms.gczms;

public class Test {

    public static void main(String[] args) {
        ClassRoom classRoom = new ClassRoom("Java");
        Question question = new Question("A","java主函数如何写");
        classRoom.addObserver(new Teacher("z"));
        classRoom.addObserver(new Teacher("h"));
        classRoom.providerQuestion(question);
    }

}
