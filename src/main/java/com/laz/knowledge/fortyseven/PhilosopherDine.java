package com.laz.knowledge.fortyseven;

public class PhilosopherDine {
	static class PhiPerson implements Runnable { // 实现Runnable接口

		// 全部使用static关键字 静态成员属于整个类，当系统第一次使用该类时，就会为其分配内存空间直到该类被卸载才会进行资源回收
		// 为什么用static修饰 因为多线程多个哲学家需要共享筷子这个对象
		static Object[] chops;// static关键字修饰的变量 该类所有的对象共享同一个成员
		static int Num = 0; // 同时拿左手边筷子的人数 也是全局变量
		private int pos; // 当前哲学家的编号 私有变量

		public PhiPerson(int position, Object[] chops) { // 构造函数
			this.chops = chops;
			this.pos = position;
		}

		@Override
		public void run() { 
			while (true) {
				int right = (pos + 1) % 5; // 我右边筷子在数组中的下标
				int left = (pos) % 5;// 左边筷子在数组中的下标
				if (Num < 4) { // 最多允许4个人同时拿左手边的筷子
								// 结点1：5个进程都有可能进入这个地方
					synchronized (chops[left]) { // 锁 左手边的筷子 就是等待左边的人用我左手边的筷子吃完了后我再拿来吃。。
						Num++;// 同时拿左手边筷子的人的数量+1 //这里没有锁住num，有可能会使得在没有num++时，5个进程都进来，
						// 结点2：最多4个进程进到这个地方，因为synchronized (chops[left])后，需要left筷子的另1个进程要在外面等待
						System.out.println(Num);
						System.out.println("第" + (pos + 1) + "个哲学家拿了左手边的筷子");
						synchronized (chops[right]) {// 右手边的筷子 锁 就是等待右手边的筷子没人拿了我再拿
														// 结点3：最多有2个进程进入到这个地方，因为只有5个筷子，所以最多两个人同时拿。
							System.out.println("第" + (pos + 1) + "个哲学家拿了右手边的筷子");
							System.out.println("第" + (pos + 1) + "个哲学家正在eating");
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("第" + (pos + 1) + "个哲学家吃完了，把筷子放回了原处，开始thinking");
							Num--;// 同时拿左手边筷子的人的数量-1
						}
					}

				}
			}
		}
	}

	static class PhiEat {

		public static void main(String[] args) {
			Object[] chObject = new Object[5];
			for (int i = 0; i < 5; i++)
				chObject[i] = i; // object必须要初始化一下呀

			// 5个哲学家
			PhiPerson firThread = new PhiPerson(0, chObject);
			PhiPerson secThread = new PhiPerson(1, chObject);
			PhiPerson thirThread = new PhiPerson(2, chObject);
			PhiPerson fourThread = new PhiPerson(3, chObject);
			PhiPerson fifThread = new PhiPerson(4, chObject);
			// 开吃了
			new Thread(firThread).start();
			new Thread(secThread).start();
			new Thread(thirThread).start();
			new Thread(fourThread).start();
			new Thread(fifThread).start();
		}

	}
}
