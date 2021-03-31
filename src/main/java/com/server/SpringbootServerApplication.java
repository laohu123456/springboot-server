package com.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(value = "com.server.dao")
@EnableScheduling
public class SpringbootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServerApplication.class, args);
    }


}
