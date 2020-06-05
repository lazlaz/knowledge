package com.laz.knowledge.twentyfour;

public class DeadLockDemo {
	private Object obj1 = new Object();
	private Object obj2 = new Object();
	class A {
		public void readA() throws InterruptedException {
			synchronized (obj1) {
				System.out.println(Thread.currentThread().getName()+"获取锁"+obj1);
				Thread.sleep(5000);
				synchronized (obj2) {
					System.out.println(Thread.currentThread().getName()+"获取锁"+obj2);
					Thread.sleep(5000);
				}
			}
		}
		public void readB() throws InterruptedException {
			synchronized (obj2) {
				System.out.println(Thread.currentThread().getName()+"获取锁"+obj2);
				Thread.sleep(5000);
				synchronized (obj1) {
					System.out.println(Thread.currentThread().getName()+"获取锁"+obj2);
					Thread.sleep(5000);
				}
			}
		}
	}
	public static void main(String[] args) {
		A a = new DeadLockDemo().new A();
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				try {
					a.readA();
					a.readB();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		new Thread(r).start();
		Runnable r2 = new Runnable() {
			
			@Override
			public void run() {
				try {
					a.readB();
					a.readA();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		new Thread(r2).start();
	}
}
