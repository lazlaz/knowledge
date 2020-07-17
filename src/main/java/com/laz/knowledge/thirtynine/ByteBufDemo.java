package com.laz.knowledge.thirtynine;

import java.util.Arrays;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufDemo {
	// 堆缓冲区
	@Test
	public void test1() {
		// 创建Java堆缓冲区
		ByteBuf heapBuf = Unpooled.buffer(10);
		if (heapBuf.hasArray()) { // 是数组支撑
			byte[] array = heapBuf.array();
			int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
			int length = heapBuf.readableBytes();
			System.out.println(Arrays.toString(array));// 字节数组打印
			System.out.println(offset);
			System.out.println(length);
		}
	}

	// 直接缓冲区
	@Test
	public void test2() {
		ByteBuf directBuf = Unpooled.directBuffer(10);
		if (!directBuf.hasArray()) {
			int length = directBuf.readableBytes();
			byte[] array = new byte[length];
			directBuf.getBytes(directBuf.readerIndex(), array);
			System.out.println(Arrays.toString(array));// 字节数组打印
			System.out.println(length);
		}

	}

	// 复合缓冲区
	@Test
	public void test3() {
		// 复合缓冲区，只是提供一个视图
		CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
		ByteBuf headerBuf = Unpooled.buffer(); // can be backing or direct
		ByteBuf bodyBuf = Unpooled.directBuffer(); // can be backing or direct
		messageBuf.addComponents(headerBuf, bodyBuf);
		messageBuf.removeComponent(0); // remove the header
		for (ByteBuf buf : messageBuf) {
			System.out.println(buf.toString());
		}
	}

	// 随机访问索引
	@Test
	public void test4() {
		ByteBuf buffer = Unpooled.wrappedBuffer("abc".getBytes()); // get reference form somewhere
		for (int i = 0; i < buffer.capacity(); i++) {
			byte b = buffer.getByte(i);// 不改变readerIndex值
			System.out.println((char) b);
		}

	}

}
