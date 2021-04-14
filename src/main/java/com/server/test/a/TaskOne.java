package com.server.test.a;

import java.util.concurrent.Callable;

public class TaskOne implements Callable<Integer> {
    @Override
    public Integer call() {
        try {
            System.out.println("TASKONE");
            Thread.sleep(3000L);
            System.out.println("执行完成TaskOne");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 3000;
    }
}
