package com.laz.knowledge.twentysix;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ThreadLocalDemo {
	/*定义了1个ThreadLocal<Integer>对象，
     *并复写它的initialValue方法，初始值是3*/
    private static ThreadLocal<Integer> tlA = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 3;
        }
    };
     
    /*  
    private ThreadLocal<Integer> tlB = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 5;
        }
    };
    */
     
     
    private Random rnd = new Random();
     
    /*Worker定义为内部类实现了Runnable接口,tlA定义在外部类中，
每个线程中调用这个对象的get方法，再调用一个set方法设置一个随机值*/
    public class Worker implements Runnable{
        @Override
        public void run(){
            int valA = tlA.get();
            System.out.println(Thread.currentThread().getName() +" tlA initial val : "+ valA);
            valA = rnd.nextInt();
            tlA.set(valA);
            System.out.println(Thread.currentThread().getName() +" tlA  new     val: "+ valA);
        }
    }
     
    /*创建三个线程，每个线程都会对ThreadLocal对象tlA进行操作*/
    public static void main(String[] args){
        ExecutorService es = Executors.newFixedThreadPool(3);
        ThreadLocalDemo tld = new ThreadLocalDemo();
        es.execute(tld.new Worker());
        es.execute(tld.new Worker());
        es.execute(tld.new Worker());
        es.shutdown();
    }
}
