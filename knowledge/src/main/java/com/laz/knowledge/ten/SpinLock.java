package com.laz.knowledge.ten;

import java.util.concurrent.atomic.AtomicReference;

//Java实现乐观锁
public class SpinLock {
	private AtomicReference<Thread> cas = new AtomicReference<Thread>();

	public void lock() {
		Thread current = Thread.currentThread();
		// 利用CAS
		while (!cas.compareAndSet(null, current)) {
			// DO nothing
			System.out.println(current);
		}
	}

	public void unlock() {
		Thread current = Thread.currentThread();
		cas.compareAndSet(current, null);
	}
}
