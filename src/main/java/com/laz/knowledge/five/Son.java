package com.laz.knowledge.five;

class B {
	public B() {
		System.out.println("��ʼ��B");
	}
}
public class Son extends Father{
	public static B b = new B();
	static {
		int b = 0;
		System.out.println("ִ�о�̬�����");
	}
	private B bb = new B();
	public Son() {
		System.out.println("ִ���ӹ��캯��");
	}
}
