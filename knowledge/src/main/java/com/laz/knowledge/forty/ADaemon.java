package com.laz.knowledge.forty;

import java.util.concurrent.TimeUnit;

public class ADaemon implements Runnable {

	public void run() {

		try {

			System.out.println("start ADaemon...");

			TimeUnit.SECONDS.sleep(1);

		} catch (InterruptedException e) {

			System.out.println("Exiting via InterruptedException");

		} finally {

			System.out.println("This shoud be always run ?");

		}

	}

}
