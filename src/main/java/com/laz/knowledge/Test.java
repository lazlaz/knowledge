package com.laz.knowledge;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		int a=23;
		int b=30;
		double c=21d;
		//%d输出整型，%f输出浮点型，%n是转行的意思
		System.out.printf("%d%n",a);
		System.out.printf("%f",c);
		new Test().test();
		
		System.out.println("xxxx");
	}
	public synchronized void test() throws InterruptedException {
		System.out.println(Thread.holdsLock(this));
		wait();
	}
}
