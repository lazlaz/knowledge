package com.laz.knowledge.sixteen;

import org.junit.Test;

public class TestMain {
	// 数据存在丢失
	@Test
	public void addTest() {
		long l = Long.MAX_VALUE;
		double d = l / 1.0;
		double clone = d;

		System.out.println(d);
		for (int i = 0; i < 1000000000; i++) {
			clone += 1;
		}
		System.out.println(clone);
		System.out.println(clone == d);
	}

	// 存在进制转换，十进制的0.1在二进制下将是一个无线循环小数
	@Test
	public void test2() {
		float increment = 0.1f;
		float expected = 1;
		float sum = 0;
		for (int i = 0; i < 10; i++) {
			sum += increment;
			System.out.println(sum);
		}

		if (expected == sum) {
			System.out.println("equal");
		} else {
			System.out.println("not equal ");
		}
	}
}
