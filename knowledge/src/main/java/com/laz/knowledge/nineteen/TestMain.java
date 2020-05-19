package com.laz.knowledge.nineteen;

public class TestMain extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+"hello world");

    }

        

    public static void main(String[] args) {

        TestMain t1 = new TestMain();

        TestMain t2 = new TestMain();
        
        TestMain t3 = new TestMain();

        t1.start();

        t2.start();
        
        t3.start();

    }
}
