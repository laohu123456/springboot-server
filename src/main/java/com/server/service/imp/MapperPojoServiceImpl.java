package com.server.service.imp;

import com.server.config.mybatisecret.MapperPojo;
import com.server.dao.MapperPojoMapper;
import com.server.service.MapperPojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MapperPojoServiceImpl implements MapperPojoService {

    @Autowired
    private MapperPojoMapper pojoMapper;

    @Transactional
    @Override
    public boolean insert(MapperPojo mapperPojo) {
        return pojoMapper.insert(mapperPojo) > 0;
    }

    @Override
    public List<MapperPojo> findAll() {
        return pojoMapper.findAll();
    }

    @Override
    public MapperPojo findById(Integer id) {
        return pojoMapper.findById(id);
    }
}
