package com.laz.knowledge.fiftytwo;

import org.junit.Test;

public class BinaryOperation {
	@Test
	// 按位与
	public void test1() {
		int i = 0B100; // 十进制为4
		int j = 0B101; // 十进制为5
		// 二进制结果：100
		// 十进制结果：4
		System.out.println("二进制结果：" + Integer.toBinaryString(i & j));
		System.out.println("十进制结果：" + (i & j));
	}
	
	//非
	@Test
	public void test2() {
	    int i = 0B100; // 十进制为4

	    // 二进制结果：11111111111111111111111111111011
	    // 十进制结果：-5
	    System.out.println("二进制结果：" + Integer.toBinaryString(~i));
	    System.out.println("十进制结果：" + (~i));
	}
	
	//异或
	@Test
	public void test3() {
	    int i = 0B100; // 十进制为4
	    int j = 0B101; // 十进制为5

	    // 二进制结果：1
	    // 十进制结果：1
	    System.out.println("二进制结果：" + Integer.toBinaryString(i ^ j));
	    System.out.println("十进制结果：" + (i ^ j));
	}
	
	//左移
	@Test
	public void test4() {
	    int i = 0B100; // 十进制为4

	    // 二进制结果：100000
	    // 十进制结果：32 = 4 * (2的3次方)
	    System.out.println("二进制结果：" + Integer.toBinaryString(i << 2));
	    System.out.println("十进制结果：" + (i << 3));
	}
	
	//正数右移
	@Test
	public void test5() {
	    int i = 0B100; // 十进制为4

	    // 二进制结果：10
	    // 十进制结果：2
	    System.out.println("二进制结果：" + Integer.toBinaryString(i >> 1));
	    System.out.println("十进制结果：" + (i >> 1));
	}
	
	//负数右移
	@Test
	public void test6() {
	    int i = -0B100; // 十进制为-4

	    // 二进制结果：11111111111111111111111111111110
	    // 十进制结果：-2
	    System.out.println("二进制结果：" + Integer.toBinaryString(i >> 1));
	    System.out.println("十进制结果：" + (i >> 1));
	}

	//>>>：无符号右移
	@Test
	public void test7() {
	    int i = -0B100; // 十进制为-4

	    // 二进制结果：11111111111111111111111111111110（>>的结果）
	    // 二进制结果：1111111111111111111111111111110（>>>的结果）
	    // 十进制结果：2147483646
	    System.out.println("二进制结果：" + Integer.toBinaryString(i >>> 1));
	    System.out.println("十进制结果：" + (i >>> 1));
	}


}
