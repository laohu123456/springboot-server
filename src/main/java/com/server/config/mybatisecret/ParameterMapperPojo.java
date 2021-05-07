package com.server.config.mybatisecret;

import com.server.secret.Md5;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @Intercepts 注解开启拦截器，@Signature 注解定义拦截器的实际类型。
 *
 * @Signature中
 *
 * type 属性指定当前拦截器使用StatementHandler 、ResultSetHandler、ParameterHandler，Executor的一种
 * method 属性指定使用以上四种类型的具体方法（可进入class内部查看其方法）。
 * args 属性指定预编译语句
 *
 * 拦截执行器的方法 Executor.class (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * 拦截参数的处理 ParameterHandler.class (getParameterObject, setParameters)
 * 拦截结果集的处理 ResultSetHandler.class (handleResultSets, handleOutputParameters)
 * 拦截Sql语法构建的处理 StatementHandler.class (prepare, parameterize, batch, update, query)
 */

@Component
@Intercepts(
        @Signature(type = ParameterHandler.class
                , method = "setParameters"
                , args = {PreparedStatement.class})

        /*@Signature(type = Executor.class
        , method = "setParameters"
        , args = {PreparedStatement.class})*/
)
public class ParameterMapperPojo implements Interceptor {

    //private static final String[] PERFIX = {"insert", "update"};


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ParameterHandler parameterHandler = (ParameterHandler)invocation.getTarget();
        /**
         * {@link DefaultParameterHandler}
         * parameterObject boundSql
         */
        Field parameterObject = parameterHandler.getClass().getDeclaredField("parameterObject");

        parameterObject.setAccessible(true);
        //get方法是返回参数对象的值
        Object o = parameterObject.get(parameterHandler);
        if(o != null){
            Class<?> aClass = o.getClass();  //获取参数类型 --> 实体类型MapperPojo
            boolean annotationPresent = aClass.isAnnotationPresent(NeedSecret.class);
            if(!annotationPresent){
                invocation.proceed();
            }
            Field[] declaredFields = aClass.getDeclaredFields();
            List<Field> fields = Arrays.asList(declaredFields);
            fields.stream()
                    .filter(field -> field.isAnnotationPresent(NeedMapperSecret.class))
                    .forEach(field -> {
                        try {
                            field.setAccessible(true);
                            Object o1 = field.get(o);
                            if(o1 instanceof String){
                                String value = (String) o1;
                                field.set(o, Md5.encrypt(value));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
        }
        return invocation.proceed();
    }

    /**
     * 切记配置，否则当前拦截器不会加入拦截器链
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    //自定义配置写入，没有自定义配置的可以直接置空此方法
    @Override
    public void setProperties(Properties properties) {

    }

}
