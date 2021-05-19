package com.server.redis;

import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class A extends LinkedHashMap {

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return true;
    }
}
