package com.laz.knowledge.fiftyeight;

/**
 * 流量控制漏桶算法
 *https://blog.csdn.net/zazzh007/article/details/104034408
 */
public class Funnel {
	private static final long NANO = 1000000000;

	private long capacity;
	private long leftQuota;
	private long leakingTs;
	private int rate;

	/**
	 * 构造函数
	 * 
	 * @param capatity 容量
	 * @param rate 每秒漏水数量
	 */
	public Funnel(int capatity, int rate) {
		this.capacity = capatity;
		this.leakingTs = System.nanoTime();
		this.rate = rate;
	}

	/**
	 * 补水
	 */
	private void makeSpace() {
		long now = System.nanoTime();
		long time = now - leakingTs;
		long leaked = time * rate / NANO;
		if (leaked < 1) {
			return;
		}
		leftQuota += leaked;

		if (leftQuota > capacity) {
			leftQuota = capacity;
		}
		leakingTs = now;
	}
	
	/**
	 * 漏水。桶里水量不够就返回false
	 * @param quota 漏水量
	 * @return 是否漏水成功
	 */
	public boolean tryWatering(int quota) {
		makeSpace();
		long left = leftQuota - quota;
		if (left >= 0) {
			leftQuota = left;
			return true;
		}
		return false;
	}

	/**
	 * 漏水。没水就阻塞直到蓄满足够的水
	 * @param quota 要漏的数量
	 */
	public void watering(int quota) {
		long left;
		try {
			do {
				makeSpace();
				left = leftQuota - quota;
				if (left >= 0) {
					leftQuota = left;
				} else {
					Thread.sleep(1);
				}
			} while (left < 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
