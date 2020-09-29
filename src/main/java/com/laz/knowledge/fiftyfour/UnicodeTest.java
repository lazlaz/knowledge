package com.laz.knowledge.fiftyfour;

import org.junit.Test;

public class UnicodeTest {
	// 有特殊符号
	@Test
	public void test1() {
		String str = "‪D:\\\\test";
		String str2 = "D:\\\\test";
		System.out.println(str.equals(str2));
	}

	// 无特殊符号
	@Test
	public void test2() {
		String str = "D:\\\\test";
		String str2 = "D:\\\\test";
		System.out.println(str.equals(str2));
	}

	// 特殊符号处理后
	@Test
	public void test3() {
		String str = "‪D:\\\\test";
		String str2 = "D:\\\\test";
		str = deleteInvalidUnicode(str);
		System.out.println(str.equals(str2));
	}

	public static String deleteInvalidUnicode(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}
		String newStr = str;
		// 从左至右嵌入 表情符号 (U+202A) - Unicode®字符百科
		if (str.charAt(0) == 8234) {
			newStr = str.substring(1);
		}
		return newStr;
	}
}
