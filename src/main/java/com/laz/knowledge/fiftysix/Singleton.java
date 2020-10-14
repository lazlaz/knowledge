package com.laz.knowledge.fiftysix;

//单例模式
public class Singleton {
	private static volatile Singleton instance = null;// volatile的⼀个语义是禁⽌指令重排序优化

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {// 2
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1==s2);
	}
}
