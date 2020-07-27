package com.laz.knowledge.fortyfive;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceTest {
	public static void main(String[] args) throws InterruptedException {
		WeakReference<Obj> weakReference = new WeakReference<>(new Obj());
		System.out.println(weakReference.get() == null);// false
		System.gc();
		TimeUnit.SECONDS.sleep(1);// 暂停一秒钟
		System.out.println(weakReference.get() == null);// true
	}
}
