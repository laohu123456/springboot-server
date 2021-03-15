package com.server.redis.servcie.impl;

import com.server.constant.CommonConstant;
import com.server.dao.PathMapper;
import com.server.redis.servcie.RedisSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisSetServiceImpl implements RedisSetService {

    @Autowired
    @Qualifier("redisTemplateSer")
    private RedisTemplate redisTemplate;

    @Autowired
    private PathMapper pathMapper;

    @Override
    public void insertZset() {
        redisTemplate.delete(CommonConstant.getNotLoginAllowPath());
        List<String> interceptorPath = pathMapper.findInterceptorPath();
        SetOperations setOperations = redisTemplate.opsForSet();
        for(String string:interceptorPath){
            setOperations.add(CommonConstant.getNotLoginAllowPath(), string);
        }
    }



    @Override
    public boolean findExist(String path) {
        Boolean ifExist = redisTemplate.hasKey(CommonConstant.getNotLoginAllowPath());
        if(ifExist){
            return redisTemplate.opsForSet().isMember(CommonConstant.getNotLoginAllowPath(), path);
        }
        return false;
    }

}
