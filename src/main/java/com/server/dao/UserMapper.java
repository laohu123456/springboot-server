package com.server.dao;

import com.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findUserByNameAndPasswd(@Param("user_name") String user_name
            , @Param("user_password") String user_password);

    User findUserByName(@Param("user_name") String user_name);

    int insertUser(User user);


}
