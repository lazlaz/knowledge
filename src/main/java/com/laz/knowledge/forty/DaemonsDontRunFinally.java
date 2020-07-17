package com.laz.knowledge.forty;

public class DaemonsDontRunFinally {
	/**
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {
		// 如果用户线程已经全部退出运行了，只剩下守护线程存在了，虚拟机也就退出了
		Thread t = new Thread(new ADaemon());

		t.setDaemon(true);

		t.start();

	}
}
