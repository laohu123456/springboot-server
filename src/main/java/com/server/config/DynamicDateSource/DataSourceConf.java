package com.server.config.DynamicDateSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

///@Component
public class DataSourceConf {

    @Autowired
    private DruidMasterConfig druidMasterConfig;

    @Autowired
    private DruidSlaveConfig druidSlaveConfig;

    @Bean("getCurrentSqlSession")
    public DataSource getCurrentSqlSession(){
        DataSource dataSourceMaster = druidMasterConfig.druidDataSource();
        DataSource dataSourceSlave = druidSlaveConfig.druidDataSource();
        Map<Object, Object> map = new HashMap<>();
        map.put(DynamicDataSource.DataSourceType.MASTER, dataSourceMaster);
        map.put(DynamicDataSource.DataSourceType.SLAVE, dataSourceSlave);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceMaster);
        return dynamicDataSource;
    }
}
