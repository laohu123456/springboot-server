package com.server.dao;

import com.server.config.mybatisecret.MapperPojo;
import com.server.config.mybatisecret.SecretField;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MapperPojoMapper {

    @SecretField
    int insert(MapperPojo mapperPojo);

    @SecretField
    List<MapperPojo> findAll();

    @SecretField
    MapperPojo findById(@Param("id")Integer id);

}
