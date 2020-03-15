package com.laz.knowledge.five;

class B {
	public B() {
		System.out.println("初始化B");
	}
}
public class Son extends Father{
	public static B b = new B();
	static {
		int b = 0;
		System.out.println("执行静态代码块");
	}
	private B bb = new B();
	public Son() {
		System.out.println("执行子构造函数");
	}
}
