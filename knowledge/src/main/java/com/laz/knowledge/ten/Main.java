package com.laz.knowledge.ten;

public class Main {
	public static void main(String[] args) {
		Resoure r = new Resoure();
		
		Thread a = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				r.add();
			}
		});
		a.start();
		
	   Thread b = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				r.add();
			}
		});
		b.start();
	}
	
}

class Resoure {
	int a = 0;
	SpinLock lock = new SpinLock();
	public void add() {
		lock.lock();
		a++;
		System.out.println(a);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.unlock();
	}
}