package com.laz.knowledge.fiftyeight;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class FunnelTest {
	/** 纳秒 */
	private static final long NANO = 1000000000;

	private volatile static long totalCount = 0;
	private volatile static long totalSize = 0;

	private final static int MAX_PACKET_SIZE_KB = 100; // 每个数据包50KB
	private final static int THRESHOLD_MB = 50; // 每秒50MB流量

	public static final void main(String[] args) {
		startLogger();

		// 测试数据包
		Random rand = new Random();
		// 构造漏桶，这里设置为3个水滴（数据包）大小， 应根据实际情况调整，不能小于1，否则相当于桶容量不到一滴水的量，当然就无法漏水，即流量为0
		// 容量越小，流量在微观时间片内的速率越不会超出流量限制，但在水滴大小变化较大、剩余水量刚好不够一滴水
		// 等情况下，很容易进入等待补水的状态，导致速度低于预设值；
		// 容量越大，宏观速度越稳定接近设定值，但细粒度时间内速度波动范围可能比较大，短时间内漏快了（超速）也可以
		Funnel funnel = new Funnel(3 * MAX_PACKET_SIZE_KB * 1024, THRESHOLD_MB * 1024 * 1024);
		do {
			byte[] data = new byte[rand.nextInt(100) * 1024];
			funnel.watering(data.length);

			totalCount++;
			totalSize += data.length;
		} while (true);
	}

	/**
	 * 数据统计线程
	 */
	private static void startLogger() {
		Timer logTimer = new Timer(true);
		logTimer.schedule(new TimerTask() {
			private long lastTs = 0;
			private long lastCount = 0;
			private long lastSize = 0;

			@Override
			public void run() {
				// 打印周期平均速度
				if (lastTs == 0) {
					// 跳过第一次
					lastTs = System.nanoTime();
					return;
				}

				long tempCount = totalCount;
				long tempSize = totalSize;
				long tempTs = System.nanoTime();

				long count = tempCount - lastCount;
				long size = tempSize - lastSize;
				long duration = tempTs - lastTs;
				float byteSpeed = (float) size * NANO / duration / (1024 * 1024);

				lastCount = tempCount;
				lastSize = tempSize;
				lastTs = tempTs;

				String log = String.format("最近%d秒共产生%d个包，%fMB，平均速度：%dMB/秒", (int) (1.0f * duration / NANO + 0.5), count,
						(float) size / (1024 * 1024), Math.round(byteSpeed));
				System.out.println(log);
			}
		}, new Date(), 5000);
	}
}
