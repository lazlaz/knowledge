package com.laz.knowledge.sixtynine;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

public class Demo1 {
	@Test
	public void test1() throws InterruptedException {
		final HashedWheelTimer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 100,
				TimeUnit.MILLISECONDS, 32);

		timer.newTimeout(new TimerTask() {
			@Override
			public void run(final Timeout timeout) throws Exception {
				System.out.println("lee"); // 打印名字
			}
		}, 1000, TimeUnit.MILLISECONDS);

		Thread.sleep(1000000);
		//Assert.assertFalse(timer.stop().isEmpty());
	}
}
