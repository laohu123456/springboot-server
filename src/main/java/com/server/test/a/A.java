package com.server.test.a;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> submit = executorService.submit(new TaskOne());
        Future<Integer> submit1 = executorService.submit(new TaskTwo());


        long end = System.currentTimeMillis();
        System.out.println("本次执行耗时: " + (end - start) / 1000);

        executorService.shutdown();
    }
}
