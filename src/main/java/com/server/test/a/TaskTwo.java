package com.server.test.a;

import java.util.concurrent.Callable;

public class TaskTwo implements Callable<Integer> {
    @Override
    public Integer call() {
        try {
            System.out.println("TASKTWO");
            Thread.sleep(6000L);
            System.out.println("执行完成TaskTwo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 6000;
    }
}
