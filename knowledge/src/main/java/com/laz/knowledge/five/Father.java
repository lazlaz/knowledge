package com.laz.knowledge.five;

class A {
	public A() {
		System.out.println("��ʼ��A");
	}
}
public class Father {
	public static A a = new A();
	static {
		int a = 0;
		System.out.println("ִ�о�̬�����");
	}
	private A aa = new A();
	public Father() {
		System.out.println("ִ�и����캯��");
	}
}
