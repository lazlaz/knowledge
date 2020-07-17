package com.laz.knowledge.thirtyfive;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r);
		t.setName("laz-----------"+t.getName()+t.getId());
		return t;
	}

}
