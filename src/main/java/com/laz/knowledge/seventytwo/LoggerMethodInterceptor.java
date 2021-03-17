package com.laz.knowledge.seventytwo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoggerMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String name = invocation.getMethod().getName();
        long startTime = System.nanoTime();
        System.out.println(String.format("before method[%s] at %s", name, startTime));

        Object obj = null;
        try {
            obj = invocation.proceed();// 执行服务
        } finally {
            long endTime = System.nanoTime();
            System.out.println(String.format("after method[%s] at %s, cost(ns):%d", name, endTime, (endTime - startTime)));
        }
        return obj;
    }

}
