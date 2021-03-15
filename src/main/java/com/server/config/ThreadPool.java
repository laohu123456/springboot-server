package com.server.config;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public static ThreadPoolExecutor threadPoolExecutor
             = new ThreadPoolExecutor
            (5, 8, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(5));

}
