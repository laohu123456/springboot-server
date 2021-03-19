package com.server.dao;

import com.server.config.DynamicDateSource.Master;
import com.server.config.DynamicDateSource.Slave;
import com.server.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    User findUserByNameAndPasswd(@Param("user_name") String user_name
            , @Param("user_password") String user_password);

    @Slave
    User findUserByName(@Param("user_name") String user_name);

    @Master
    int insertUser(User user);


}
