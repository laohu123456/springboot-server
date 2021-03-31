package com.server.test.quarzt;

import com.server.utils.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("----------------------------");
        System.out.println("当前时间 :" + DateUtils.getCurrentDate());
        System.out.printf("Hello World");
    }
}
