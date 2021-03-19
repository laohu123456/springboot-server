package com.server.config.DynamicDateSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private final static ThreadLocal<DataSourceType> DATASOURCE_QH = new ThreadLocal<DataSourceType>(){
        @Override
        protected DataSourceType initialValue() {
            return DataSourceType.MASTER;
        }
    };

    @Override
    protected Object determineCurrentLookupKey() {
        return DATASOURCE_QH.get();
    }

    public static enum DataSourceType{
        MASTER, SLAVE
    }

    public static void master(){
        DATASOURCE_QH.set(DataSourceType.MASTER);
    }

    public static void slave(){
        DATASOURCE_QH.set(DataSourceType.SLAVE);
    }

    public static void setCurrentDataSource(DataSourceType dataSource){
        DATASOURCE_QH.set(dataSource);
    }

    public static DataSourceType getDataSourceType(){
        return DATASOURCE_QH.get();
    }


}
