package com.laz.knowledge.nine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableDemo {
	  private static final int CORE_POOL_SIZE = 5;
	    private static final int MAX_POOL_SIZE = 10;
	    private static final int QUEUE_CAPACITY = 100;
	    private static final Long KEEP_ALIVE_TIME = 1L;

	    public static void main(String[] args) {

	        ThreadPoolExecutor executor = new ThreadPoolExecutor(
	                CORE_POOL_SIZE,
	                MAX_POOL_SIZE,
	                KEEP_ALIVE_TIME,
	                TimeUnit.SECONDS,
	                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
	                new ThreadPoolExecutor.CallerRunsPolicy());

	        List<Future<String>> futureList = new ArrayList<>();
	        Callable<String> callable = new MyCallable();
	        for (int i = 0; i < 10; i++) {
	            Future<String> future = executor.submit(callable);
	            futureList.add(future);
	        }
	        for (Future<String> fut : futureList) {
	            try {
	                System.out.println(new Date() + "::" + fut.get());
	            } catch (InterruptedException | ExecutionException e) {
	                e.printStackTrace();
	            }
	        }
	        executor.shutdown();
	    }
}
