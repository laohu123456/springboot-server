package com.server.config.mybatisecret;

import com.server.secret.Md5;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class DecryptMapperPojo implements Interceptor {

    //private static final String[] PERFIX = {"find", "query", "select"};

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object proceed = invocation.proceed();
        if(proceed == null){
            return proceed;
        }

        if(proceed instanceof ArrayList){
            ArrayList arrayList = (ArrayList) proceed;
            if(!CollectionUtils.isEmpty(arrayList)){
               if(!need(arrayList.get(0))){
                    for(Object result: arrayList){
                        Decrypt(result);
                    }
                }
            }
        }

        if(!need(proceed)){
            Decrypt(proceed);
        }

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public boolean need(Object o){
        Class<?> aClass = o.getClass();
        boolean annotationPresent = aClass.isAnnotationPresent(NeedSecret.class);
        if(!annotationPresent){
            return true;
        }
        List<Field> collect = Arrays.asList(aClass.getDeclaredFields()).stream()
                .filter(field -> field.isAnnotationPresent(NeedMapperSecret.class)).collect(Collectors.toList());
        return CollectionUtils.isEmpty(collect);
    }

    public void Decrypt(Object result){
        Class<?> aClass = result.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        Arrays.asList(declaredFields)
                .stream()
                .filter(field -> field.isAnnotationPresent(NeedMapperSecret.class))
                .forEach(field -> {
                   try{
                       field.setAccessible(true);
                       Object o = field.get(result);
                       if(o instanceof String){
                           String value = (String) o;
                           field.set(result, Md5.dencrypt(value));
                       }
                   } catch (IllegalAccessException e) {
                       e.printStackTrace();
                   }
                });
    }

}
