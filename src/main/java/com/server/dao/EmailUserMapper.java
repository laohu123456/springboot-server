package com.server.dao;

import com.server.entity.EmailUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmailUserMapper {

    @Insert( value = "INSERT INTO a_email " +
            "(id, email_name, email_passwd) " +
            "values " +
            "(#{id}, #{emailName}, #{emailPasswd})")
    int insertPojo(EmailUser emailUser);


    @Select(value = "SELECT " +
            "id as id , email_name as emailName, email_passwd as emailPasswd  " +
            "from a_email " +
            "where " +
            "email_name = #{name}")
    EmailUser findEmailByName(@Param("name")String name);

}
