package com.laz.knowledge.nine;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
	  private static final int CORE_POOL_SIZE = 5;
	    private static final int MAX_POOL_SIZE = 10;
	    private static final int QUEUE_CAPACITY = 100;
	    private static final Long KEEP_ALIVE_TIME = 1L;
	    public static void main(String[] args) {

	        //ʹ�ð���Ͱ��Ƽ��Ĵ����̳߳صķ�ʽ
	        //ͨ��ThreadPoolExecutor���캯���Զ����������
	        ThreadPoolExecutor executor = new ThreadPoolExecutor(
	                CORE_POOL_SIZE,
	                MAX_POOL_SIZE,
	                KEEP_ALIVE_TIME,
	                TimeUnit.SECONDS,
	                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
	                new ThreadPoolExecutor.CallerRunsPolicy());

	        for (int i = 0; i < 10; i++) {
	            //����WorkerThread����WorkerThread��ʵ����Runnable �ӿڣ�
	            Runnable worker = new MyRunnable("" + i);
	            //ִ��Runnable
	            executor.execute(worker);
	        }
	        //��ֹ�̳߳�
	        executor.shutdown();
	        while (!executor.isTerminated()) {
	        }
	        System.out.println("Finished all threads");
	    }
}
