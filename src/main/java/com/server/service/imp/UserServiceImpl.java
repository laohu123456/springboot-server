package com.server.service.imp;

import com.server.config.LocalSession;
import com.server.constant.CommonConstant;
import com.server.dao.UserMapper;
import com.server.entity.ExceptionEntity;
import com.server.entity.User;
import com.server.redis.servcie.RedisStringService;
import com.server.service.UserService;
import com.server.utils.GetRedisKey;
import com.server.utils.JwtUtils;
import com.server.utils.OtherUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisStringService redisStringService;


    @Override
    public Map<String, Object> findUserByNameAndPasswd(String user_name, String user_password) {
        Map<String, Object> map = new HashMap<>();
        User user = userMapper.findUserByName(user_name);
        if (user == null) {
            map.put("code", "-1");
            map.put("message", "用户名不存在");
        } else {
            if (user.getPassWord().equals(user_password)) {
                String token = JwtUtils.createJWT(user.getUserId());
                map.put("code", "200");
                map.put("titck", JwtUtils.TOKEN_PREFIX + token);
                map.put("message", "登录成功");
                String key = GetRedisKey.getLoginJwtTime(user.getUserId());
                redisStringService.expire(key, token, CommonConstant.getLoginJwtExpireTime());
            } else {
                String key = GetRedisKey.getLoginErrorSeconds(user.getUserId());
                redisStringService.increNum(key);
                map.put("code", "-1");
                map.put("message", "密码不正确");
            }
        }
        return map;
    }

    @Override
    public ExceptionEntity oauthUser(String token) {
        ExceptionEntity exceptionEntity = null;
        try{
            if (StringUtils.isEmpty(token)) {
                exceptionEntity = new ExceptionEntity(400, "Authorization is null", 10010);
                return exceptionEntity;
            }
            String titck = token.substring(JwtUtils.TOKEN_PREFIX.length(), token.length());
            String user_id = JwtUtils.getUserId(titck);
            String key = GetRedisKey.getLoginJwtTime(user_id);
            boolean exist = redisStringService.exist(key);
            if (!exist) {
                exceptionEntity = new ExceptionEntity(400, "token is not exist", 10010);
                return exceptionEntity;
            }
            long ttl = redisStringService.ttl(key);
            //titck是否过期
            boolean passExpire = OtherUtils.equalLongValue(ttl, -2L);
            if (passExpire) {
                exceptionEntity = new ExceptionEntity(400, "token is expire", 10010);
                return exceptionEntity;
            }
            //比较titck是否一致
            String redisToken = String.valueOf(redisStringService.get(key));
            if (!redisToken.equals(titck)) {
                exceptionEntity = new ExceptionEntity(400, "token is illegal", 10010);
                return exceptionEntity;
            }
            redisStringService.expire(key, titck, CommonConstant.getLoginJwtExpireTime());
            LocalSession.set(user_id);
            exceptionEntity = new ExceptionEntity(200, "token normal", 10009);
        }catch (Exception e){
            exceptionEntity = new ExceptionEntity(400, "token Oauth exception", 10011);
            e.printStackTrace();
        }
        return exceptionEntity;
    }

    @Transactional
    @Override
    public Map<String, Object> rejisterUser(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        String uuid = OtherUtils.getUUID();
        User user1 = userMapper.findUserByName(username);
        if(user1 == null){
            map.put("code", 500);
            map.put("message", "用户名已存在");
            return map;
        }
        User user = new User();
        user.setUserId(uuid);
        user.setUserName(username);
        user.setPassWord(password);
        int success = userMapper.insertUser(user);
        if(success > 0){
            map.put("code", 200);
            map.put("message", "注册成功");
        }else{
            map.put("code", 500);
            map.put("message", "注册失败");
        }
        return map;
    }

}
