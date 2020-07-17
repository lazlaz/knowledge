package com.laz.knowledge.four;

public class Main {
	public static void main(String[] args) {
		{
			long start = System.currentTimeMillis();
			testString(100000);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
		}
		{
			long start = System.currentTimeMillis();
			testStringBuffer(100000);
			long end = System.currentTimeMillis();
			System.out.println(end-start);
		}
	}

	private static String testStringBuffer(int count) {
		StringBuffer s = new StringBuffer();
		for (int i=0;i<count;i++) {
			s.append(i+"");
		}
		
		return s.toString();
		
	}

	private static String testString(int count) {
		String s = "";
		for (int i=0;i<count;i++) {
			s+=i+"";
		}
		
		return s;
	}
}
