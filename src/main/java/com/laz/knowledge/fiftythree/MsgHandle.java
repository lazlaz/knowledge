package com.laz.knowledge.fiftythree;

/**
 * 后台启动一条线程，不断检测是否要刷新重新加载，实现了热加载的类
 *
 */
public class MsgHandle implements Runnable {
    @Override
    public void run() {
        while (true) {
            BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
            manager.logic();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
