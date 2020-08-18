package com.laz.knowledge.fortyseven;

public class PhilosopherDine2 {
	public static void main(String[] args) {
		Chop fiveChops = new PhilosopherDine2().new Chop();
		new PhilosopherDine2().new Philosopher(0, fiveChops).start();
		new PhilosopherDine2().new Philosopher(1, fiveChops).start();
		new PhilosopherDine2().new Philosopher(2, fiveChops).start();
		new PhilosopherDine2().new Philosopher(3, fiveChops).start();
		new PhilosopherDine2().new Philosopher(4, fiveChops).start();
	}

	class Philosopher extends Thread {
		private int index;
		private Chop chop;

		public Philosopher(int index, Chop chop) {
			this.index = index;
			this.chop = chop;
		}

		@Override
		public void run() {
			while (true) {
				thinking();
				chop.takeChop(index);
				eating();
				chop.putChop(index);
			}
		}

		private void thinking() {
			System.out.println("第" + index + "个哲学家正在思考...");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void eating() {
			System.out.println("第" + index + "个哲学家正在吃饭...");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	class Chop {
		private Boolean[] chops = { false, false, false, false, false };

		public synchronized void takeChop(int index) {
			while (chops[index] || chops[(index + 1) % 5]) {
				try {
					wait(); // 拿不到筷子就会被阻塞 进入等待池 从而不会再来竞争
				} catch (Exception e) {
				}
			}
			chops[index] = true;
			chops[(index + 1) % 5] = true;
		}

		public synchronized void putChop(int index) {
			chops[index] = false;
			chops[(index + 1) % 5] = false;
			notifyAll();
		}

	}

}
