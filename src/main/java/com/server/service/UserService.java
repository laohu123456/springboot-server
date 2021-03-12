package com.server.service;

import com.server.entity.ExceptionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserService {

    Map<String, Object> findUserByNameAndPasswd(@Param("user_name") String user_name
            , @Param("user_password") String user_password);

    ExceptionEntity oauthUser(String token);

    Map<String, Object> rejisterUser(String username, String password, String useremail);
}
