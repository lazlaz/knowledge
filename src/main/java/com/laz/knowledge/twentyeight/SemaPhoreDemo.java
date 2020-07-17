package com.laz.knowledge.twentyeight;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaPhoreDemo {
	public static ExecutorService threadPool = Executors.newCachedThreadPool();
	public static final int cars = 6;
	public static final int part = 2;

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(cars);
		Semaphore sp = new Semaphore(part, false);
		for (int i = 0; i < cars; i++) {
			final int j = i;
			threadPool.execute(() -> {
				try {
					sp.acquire(1);
					System.out.println("car " + j + " 进入停车位 \n");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					sp.release();
					latch.countDown();
					System.out.println("********* car " + j + " 离开停车位\n");
				}
			});
		}
		try {
			latch.await();
			System.out.println("################### 所有车辆离开  ##################");
			threadPool.shutdown();
		} catch (InterruptedException e) {
		}

	}
};