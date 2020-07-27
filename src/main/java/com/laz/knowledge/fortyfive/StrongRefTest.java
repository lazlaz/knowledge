package com.laz.knowledge.fortyfive;

import java.util.concurrent.TimeUnit;

public class StrongRefTest {
	public static void main(String[] args) throws InterruptedException {
		StrongRefTest object = new StrongRefTest();

		System.gc();
		TimeUnit.SECONDS.sleep(1);// 暂停一秒钟

		System.out.println(object == null);// false
	}

}
