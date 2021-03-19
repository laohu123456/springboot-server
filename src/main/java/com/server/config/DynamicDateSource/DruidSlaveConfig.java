package com.server.config.DynamicDateSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

//@ConfigurationProperties(prefix = "slave.datasource")
//@Configuration
public class DruidSlaveConfig {

    @Value("${slave.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${slave.datasource.url}")
    private String url;

    @Value("${slave.datasource.data-username}")
    private String username;

    @Value("${slave.datasource.data-password}")
    private String password;

    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.driverClassName);
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);

        druidDataSource.setInitialSize(5);
        druidDataSource.setMaxActive(30);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxWait(60000);


        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }

    /*@Bean
    public SqlSessionFactoryBean getSlaveConnect(@Qualifier("druidDataSourceSlave") DataSource druidDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        return sqlSessionFactoryBean;
    }*/

}
