package com.laz.knowledge.thirtyfive;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

public class PoolMemoryTest {
	@Test
	public void unPoolTest() {
		long begin = System.currentTimeMillis();
		ByteBuf buf = null;
		int maxTimes = 100_000_000;
		for (int i=0; i<maxTimes;i++) {
			buf = Unpooled.buffer(10*1024);
			buf.release();
		}
		long end = System.currentTimeMillis();
		System.out.println("Execute "+maxTimes+" times cost time: "+(end-begin));
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void poolTest() {
		long begin = System.currentTimeMillis();
		PooledByteBufAllocator allocator = new PooledByteBufAllocator(false);
		ByteBuf buf = null;
		int maxTimes = 100_000_000;
		for (int i=0; i<maxTimes;i++) {
			buf = allocator.heapBuffer(10*1024);
			buf.release();
		}
		long end = System.currentTimeMillis();
		System.out.println("Execute "+maxTimes+" times cost time: "+(end-begin));
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
