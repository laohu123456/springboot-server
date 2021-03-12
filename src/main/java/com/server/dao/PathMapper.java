package com.server.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PathMapper {

    @Select(value = "select path from a_path where status = 0")
    List<String> findInterceptorPath();

    @Select(value = "select count(*) from a_path where path = #{path}")
    int findInterceptorPathByPath(String path);
}
