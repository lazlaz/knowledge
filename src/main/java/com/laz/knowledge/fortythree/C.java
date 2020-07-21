package com.laz.knowledge.fortythree;

public class C {
	public  static String STR = "aa";
	static {
		System.out.println("CCC");
	}
	
	public static class D {
		public void init() {
			new A.B();
		}
	}
}
