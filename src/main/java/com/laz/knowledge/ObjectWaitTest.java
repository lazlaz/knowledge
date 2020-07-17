package com.laz.knowledge;

public class ObjectWaitTest {
	 public static Object waitObject = new Object();
	    
	    public static void notifyAllThread() {
	        System.out.println("notifyAllThread");
	        synchronized (waitObject) {
	            waitObject.notifyAll();
	        }
	    }
	    public static void notifyThread() {
	        System.out.println("notifyThread");
	        synchronized (waitObject) {
	            waitObject.notify();
	        }
	    }
	    public static void main(String[] args) {
	        MyThread tm1 = new MyThread(waitObject);
	        tm1.setName("tm1");
	        tm1.start();
	        MyThread tm2 = new MyThread(waitObject);
	        tm2.setName("tm2");
	        tm2.start();
	        try {
	            Thread.currentThread().sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        tm1.suspendThread();
	        tm2.suspendThread();
	        try {
	            Thread.currentThread().sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        notifyThread();
	        try {
	            Thread.currentThread().sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        notifyThread();    
	    }
	    
	    static class MyThread extends Thread {
	        public Object waitObject = null;
	        private boolean isStop = false;

	        public MyThread(Object waitObject) {
	            this.waitObject = waitObject;
	        }

	        public void run() {
	            while (true) {
	                synchronized (waitObject) {
	                    if (isStop) {
	                        System.out.println(Thread.currentThread().getId() + " is stop");
	                        try {
	                            waitObject.wait();
	                        } catch (InterruptedException e) {
	                            // TODO Auto-generated catch block
	                            e.printStackTrace();
	                        }
	                        System.out.println(Thread.currentThread().getId() + " is resume");
	                        System.out.println(Thread.currentThread().getId() + " will  exit");
	                        throw new RuntimeException(Thread.currentThread().getId() +" exit");
	                    }
	                }
	            }
	        }

	        public void suspendThread() {
	            this.isStop = true;
	        }
	    }

}
