package com.laz.knowledge.fortyfive;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class WeakReferenceTest {
	public static void main(String[] args) throws InterruptedException {
		Obj obj = new Obj();
		WeakReference<Obj> weakReference = new WeakReference<>(obj);
		System.out.println(weakReference.get() == null);// false
		obj = null;
		System.gc();
		TimeUnit.SECONDS.sleep(1);// 暂停一秒钟
		System.out.println(weakReference.get() == null);// true
	}
}
