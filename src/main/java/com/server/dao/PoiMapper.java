package com.server.dao;

import com.server.entity.Poi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PoiMapper {

    @Select(value = "select id, name, SUBSTR(password, 1, 3) as password,  SUBSTR(remark, 1, 2) as remark  from a_poi  limit 0, 60000")
    List<Poi> findAll();


    int insertData(List<Poi> list);

}
