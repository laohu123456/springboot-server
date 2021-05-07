package com.server.config.mybatisecret;

public class A {

    /**
     * Mybatis 操作数据进行加密解密  (Interceptor)
     *ParameterHandler  入参加密 
     *
     * ResultSetHandler 查询结果解密
     */


    /**
     *  谈一谈对mybatis拦截器的理解
     *
     *   * 拦截执行器的方法 Executor.class (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
     *  * 拦截参数的处理 ParameterHandler.class (getParameterObject, setParameters)
     *  * 拦截结果集的处理 ResultSetHandler.class (handleResultSets, handleOutputParameters)
     *  * 拦截Sql语法构建的处理 StatementHandler.class (prepare, parameterize, batch, update, query)
     *
     *  拿 Executor.class说明
     *  int update(MappedStatement ms, Object parameter) throws SQLException;
     *
     *  对于注解的写法就是
     *          /*@Signature(type = Executor.class
     *         , method = "update"
     *         , args = {MappedStatement.class, Object.class})
     *
     *
     *         invocation.getTarget();   ---> 被代理类 Executor.class
     *         invocation.getArgs();     ---> MappedStatement ms, Object parameter(update方法中的两个参数)
     *         invocation.proceed();     ---> 执行方法
     *         invocation.getMethod();   ---> 被代理的方法 update
     *
     *
     *         invocaton.getArgs()[0] == MappedStatement ---> 1.BoundSql(包含SQL信息) 2.id == namespace + sql语句的id
     */
}
