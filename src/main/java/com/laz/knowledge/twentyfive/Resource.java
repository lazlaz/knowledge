package com.laz.knowledge.twentyfive;

public class Resource {
	// 当前资源的数量
	int num = 0;
	// 当前资源的上限
	int size = 10;

	// 消费资源
	public synchronized void remove() {
		// 如果num为0，没有资源了，需要等待
		while (num == 0) {// 这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
			try {
				System.out.println("消费者进入等待");
				this.wait();// 线程等待，并释放锁
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 如果线程可以执行到这里，说明资源里有资源可以消费
		num--;
		System.out.println("消费者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
		this.notify();// 唤醒其他正在等待的线程
	}

	// 生产资源
	public synchronized void put() {
		// 如果资源满了，就进入阻塞状态
		while (num == size) {// 这里jdk源码里推荐用while，因为有可能出现虚假唤醒，所以要再次确认
			try {
				System.out.println("生产者进入等待");
				this.wait();// 线程进入阻塞状态，并释放锁

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		num++;
		System.out.println("生产者线程为:" + Thread.currentThread().getName() + "--资源数量:" + num);
		this.notify();// 唤醒其他正在等待的线程
	}

}
