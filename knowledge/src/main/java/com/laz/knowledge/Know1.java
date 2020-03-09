package com.laz.knowledge;

import org.junit.Test;

public class Know1 {
	
	@Test
	public void test() {
		Double d = 3.5d;
		Double x = new Double(3.5);
		double xx = x;
		System.out.println(d+xx);
	}
}
