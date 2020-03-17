package com.laz.knowledge.seven;

public class StackTest {
	private int i = 0;
	public void a(){
		System.out.println(i++);
		a();
	}
	public static void main(String[] args) {
		StackTest j = new StackTest();
		j.a();
	}
}
