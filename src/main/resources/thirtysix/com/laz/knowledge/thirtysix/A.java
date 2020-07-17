package com.laz.knowledge.thirtysix;

import com.laz.knowledge.thirtysix.b.B;

public class A {
	private B b=new B();
	
	public A() {
	}
	
	public void test() {
		System.out.println(b.add(2, 4));
	}
}
