package com.laz.knowledge.eight;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
	public static void main(String[] args) {
		TraceThreadPoolExecutor threadPoolExecutor = new TraceThreadPoolExecutor(0,Integer.MAX_VALUE, 0L,TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>());
		for(int i = 0; i<5; i++){
			threadPoolExecutor.submit(new DivTask(100,i));
		}
	}
}
