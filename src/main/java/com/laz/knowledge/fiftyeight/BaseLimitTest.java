package com.laz.knowledge.fiftyeight;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseLimitTest {
	private Limiter limiter;
	private int maxQPS;

	public BaseLimitTest(LimiterEnum type, int qps) {
		this.limiter = LimiterFactory.getCountLimiter(type, qps);
		this.maxQPS = qps;
	}

	public void test(boolean showLog) throws Exception {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

		final CountDownLatch countDownLatch1 = new CountDownLatch(100);
		final CountDownLatch countDownLatch2 = new CountDownLatch(100);
		final CountDownLatch countDownLatch3 = new CountDownLatch(100);

		// 用于统计成功请求个数
		AtomicInteger count1 = new AtomicInteger(0);
		AtomicInteger count2 = new AtomicInteger(0);
		AtomicInteger count3 = new AtomicInteger(0);

		// 固定大小线程池
		ExecutorService executorService = Executors.newFixedThreadPool(100);

		System.out.println("====== Test 1 100 并发 ======");

		// 获取开始时间
		long startTime = System.currentTimeMillis();

		// 前 1s 平均 50 次，随后不到 1s 内发起 50 次请求
		for (int i = 0; i < 100; i++) {
			executorService.execute(() -> {
				if (limiter.tryAcquire()) {
					if (showLog)
						System.out.println("success " + sf.format(new Date()));
					count1.incrementAndGet();
				} else {
					if (showLog)
						System.out.println("failed " + sf.format(new Date()));
				}
				countDownLatch1.countDown();
			});
			// 计数器算法最多请求20次，出现突刺现象
			if (i < 50 || i > 80) {
				Thread.sleep(20);
			}
		}

		countDownLatch1.await();
		System.out.println("Time Used: " + (System.currentTimeMillis() - startTime) / 1000.0 + " success: " + count1);

		Thread.sleep(1000);
		System.out.println("====== Test 2 每秒钟请求 10 次  ======");

		startTime = System.currentTimeMillis();

		// 功能性测试，每秒平均 10 次请求
		for (int i = 0; i < 100; i++) {
			executorService.execute(() -> {
				if (limiter.tryAcquire()) {
					if (showLog)
						System.out.println("success " + sf.format(new Date()));
					count2.incrementAndGet();
				} else {
					if (showLog)
						System.out.println("failed " + sf.format(new Date()));
				}
				countDownLatch2.countDown();
			});
			// 每 10 次请求延时 1 秒
//	            if ((i + 1) % 10 == 0) {
//	                Thread.sleep(1000);
//	            }
			Thread.sleep(100);
		}

		countDownLatch2.await();
		System.out.println("Time Used: " + (System.currentTimeMillis() - startTime) / 1000.0 + " success: " + count2);

		Thread.sleep(1000);
		System.out.println("====== Test 3 每秒钟请求 20 次  ======");

		startTime = System.currentTimeMillis();

		// 前 4.5s 平均每秒 20 次请求，4.5s 后并发请求 10 次
		for (int i = 0; i < 100; i++) {
			executorService.execute(() -> {
				if (limiter.tryAcquire()) {
					if (showLog)
						System.out.println("success " + sf.format(new Date()));
					count3.incrementAndGet();
				} else {
					if (showLog)
						System.out.println("failed " + sf.format(new Date()));
				}
				countDownLatch3.countDown();
			});
//	            // 每 10 次请求延时 1 秒
//	            if ((i + 1) % 20 == 0) {
//	                Thread.sleep(1000);
//	            }
			// 测试下令牌桶算法瞬时处理一定量的请求
			if (i < 90) {
				Thread.sleep(50);
			}
		}

		countDownLatch3.await();
		System.out.println("Time Used: " + (System.currentTimeMillis() - startTime) / 1000.0 + " success: " + count3);
		executorService.shutdown();

	}
}
