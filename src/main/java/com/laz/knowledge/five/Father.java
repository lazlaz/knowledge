package com.laz.knowledge.five;

class A {
	public A() {
		System.out.println("初始化A");
	}
}
public class Father {
	public static A a = new A();
	static {
		int a = 0;
		System.out.println("执行静态代码块");
	}
	private A aa = new A();
	public Father() {
		System.out.println("执行父构造函数");
	}
}
