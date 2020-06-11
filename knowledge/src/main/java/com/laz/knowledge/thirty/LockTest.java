package com.laz.knowledge.thirty;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockTest {
	static Logger logger = LoggerFactory.getLogger(LockTest.class);
	static int count = 0;
	public static void main(String[] args) throws InterruptedException {
		int clientcount = 10;
		CountDownLatch countDownLatch = new CountDownLatch(clientcount);
		ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
		DistributedLock lock = new DistributedLock();
		long start = System.currentTimeMillis();
		for (int i = 0; i < clientcount; i++) {
			executorService.execute(() -> {

				String id = UUID.randomUUID().toString();
				try {
					lock.lock(id);
					count++;
				} finally {
					lock.unlock(id);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		long end = System.currentTimeMillis();
		logger.info("执行线程数:{},总耗时:{},count数为:{}", clientcount, end - start, count);
	}
}
