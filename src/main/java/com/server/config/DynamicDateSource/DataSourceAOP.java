package com.server.config.DynamicDateSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Objects;

//@Aspect
//@Component
//@Order(Ordered.LOWEST_PRECEDENCE - 1)
public class DataSourceAOP {



    @Around("@annotation(com.server.config.DynamicDateSource.Master)")
    public Object setWriteDataSourceType(ProceedingJoinPoint pjp) throws Throwable {
        // 1、当前数据源类型
        DynamicDataSource.DataSourceType dataSourceType= DynamicDataSource.getDataSourceType();
        try {
            // 2、如果当前是Slave，就切换到Master
            if (Objects.equals(dataSourceType, DynamicDataSource.DataSourceType.SLAVE)) {
                DynamicDataSource.master();
            }
            // 3、执行目标方法
            return pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            // 4、需要将数据源还原
            DynamicDataSource.setCurrentDataSource(dataSourceType);
        }
    }

    /**
     * Slave注解
     * @param pjp
     * @throws Throwable
     */
    @Around("@annotation(com.server.config.DynamicDateSource.Slave) && !@annotation(com.server.config.DynamicDateSource.Master)")
    public Object setReadDataSourceType(ProceedingJoinPoint pjp) throws Throwable {
        // 1、当前数据源类型
        DynamicDataSource.DataSourceType dataSourceType= DynamicDataSource.getDataSourceType();
        try {
            // 2、如果当前是Master，就切换到Slave
            if (Objects.equals(dataSourceType, DynamicDataSource.DataSourceType.MASTER)) {
                DynamicDataSource.slave();
            }
            // 3、执行目标方法
            return pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            // 4、需要将数据源还原
            DynamicDataSource.setCurrentDataSource(dataSourceType);
        }
    }



    @Bean
    public SqlSessionFactoryBean getConnect(@Qualifier("getCurrentSqlSession") DataSource druidDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource);
        return sqlSessionFactoryBean;
    }

}
