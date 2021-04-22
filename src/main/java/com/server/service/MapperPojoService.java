package com.server.service;

import com.server.config.mybatisecret.MapperPojo;

import java.util.List;

public interface MapperPojoService {

    public boolean insert(MapperPojo mapperPojo);

    public List<MapperPojo> findAll();

    public MapperPojo findById(Integer id);

}
