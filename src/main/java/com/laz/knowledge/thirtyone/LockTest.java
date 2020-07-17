package com.laz.knowledge.thirtyone;

import java.util.concurrent.TimeUnit;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;
import org.redisson.core.RLock;

public class LockTest {
	public static void main(String[] args) {
		// 构造redisson实现分布式锁必要的Config
		Config config = new Config();
		config.useSingleServer().setAddress("127.0.0.1:6379").setPassword("123456").setDatabase(0);
		// 构造RedissonClient
		RedissonClient redissonClient = Redisson.create(config);
		// 设置锁定资源名称
		RLock disLock = redissonClient.getLock("DISLOCK");
		boolean isLock;
		try {
		    //尝试获取分布式锁
		    isLock = disLock.tryLock(500, 15000, TimeUnit.MILLISECONDS);
		    if (isLock) {
		        //TODO if get lock success, do something;
		    	System.out.println("获取到锁");
		        Thread.sleep(15000);
		    }
		} catch (Exception e) {
		} finally {
		    // 无论如何, 最后都要解锁
		    disLock.unlock();
		}
	}
}
