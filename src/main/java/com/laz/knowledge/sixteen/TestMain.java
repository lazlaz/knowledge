package com.laz.knowledge.sixteen;

import org.junit.Test;

public class TestMain {
	// ���ݴ��ڶ�ʧ
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

	// ���ڽ���ת����ʮ���Ƶ�0.1�ڶ������½���һ������ѭ��С��
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
