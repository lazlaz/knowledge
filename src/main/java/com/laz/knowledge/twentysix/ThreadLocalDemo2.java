package com.laz.knowledge.twentysix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

//内存泄露示例
public class ThreadLocalDemo2 {
	private static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES,
			new LinkedBlockingQueue<>());

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 100; ++i) {
			poolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					ThreadLocal<BigObject> threadLocal = new ThreadLocal<>();
					threadLocal.set(new BigObject());
					Thread t = Thread.currentThread();
					// 其他业务代码
					System.out.println(t.getName()+"");
					
					//解决方案，需要手动移除remove对象
					threadLocal.remove();
				}
			});
			Thread.sleep(1000);
		}
	}

	static class BigObject {
		// 100M
		private byte[] bytes = new byte[100 * 1024 * 1024];
	}

}
