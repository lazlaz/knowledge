package com.laz.knowledge.fortyeight;

public class BitMapTest {
	public static void main(String[] args) {
		Bitmap map = new Bitmap(16);
		map.set(0, true);
		map.set(2, true);
		
		System.out.println(map.get(2));
	}
}
