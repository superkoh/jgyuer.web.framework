package com.jgyuer.framework.persistence.mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component("queryCountInterceptor")
@Order(2)
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class})})
public class QueryCountInterceptor implements Interceptor {
    private ThreadLocal<Integer> queryCount = new ThreadLocal<>();

    public void startCounter() {
        queryCount.set(0);
    }

    public Integer getQueryCount() {
        return queryCount.get();
    }

    public void clearCounter() {
        queryCount.remove();
    }

    public Object intercept(Invocation invocation) throws Throwable {
        Integer count = queryCount.get();
        if (count != null) {
            queryCount.set(count + 1);
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}
