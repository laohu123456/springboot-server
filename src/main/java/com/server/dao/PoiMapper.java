package com.server.dao;

import com.server.annotation.comoutingtime.Category;
import com.server.annotation.comoutingtime.CountTime;
import com.server.entity.Poi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PoiMapper {

    List<Poi> findAll();


    int insertData(List<Poi> list);

    @CountTime(category = Category.SQL)
    List<Poi> producer(@Param("offestnum")Integer offestnum);

}
