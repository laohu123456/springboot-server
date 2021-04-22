package com.server.controller;

import com.server.config.mybatisecret.MapperPojo;
import com.server.service.MapperPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "mapper")
public class MapperPojoController {

    @Autowired
    private MapperPojoService mapperPojoService;

    @RequestMapping(value = "insert")
    public String insert(MapperPojo mapperPojo){
        return mapperPojoService.insert(mapperPojo) ? "插入成功":"插入失败";
    }

    @RequestMapping(value = "findAll")
    public List<MapperPojo> findAll(){
        return mapperPojoService.findAll();
    }

    @RequestMapping(value = "findById")
    public MapperPojo findById(@RequestParam(value = "id", required = true) Integer id){
        return mapperPojoService.findById(id);
    }

}
