package com.laz.knowledge.fortyfive;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

public class PhantomReferenceTest {
	public static void main(String[] args) throws InterruptedException {
		ReferenceQueue<Obj> queue = new ReferenceQueue<>();
		Obj obj = new Obj();
		PhantomReference<Obj> phantomReference = new PhantomReference<>(obj, queue);
		System.out.println(phantomReference.get() == null);// true
		obj = null;
		System.gc();
		TimeUnit.SECONDS.sleep(1);// 暂停一秒钟
		System.out.println(phantomReference.isEnqueued());

	}
}
