package com.laz.knowledge.fortyseven;

public class ProducerAndConsumer {
	class Common {    //公共线程类
	    private int production[];
	    private int count;            //实际产品数目
	    private int BUFFER_SIZE=6;    //缓冲区大小

	    public Common(){
	        production=new int[BUFFER_SIZE];
	        count=0;
	    }

	    //从缓冲区取数据
	    public synchronized int get(){
	        int result;
	        //循环检测缓冲区是否可用
	        while (count<=0) {
	            try {
	                wait();   //等待
	            } catch (InterruptedException e) {
	                e.printStackTrace();

	            }
	        }
	        result=production[--count];
	        notifyAll();
	        return result;
	    }

	    //向缓冲区写数据
	    public synchronized void put(int newProduct){
	        //循环检测缓冲区是否可用
	        while (count>=BUFFER_SIZE){
	            try {
	                wait();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        production[count++]=newProduct;
	        notifyAll();
	    }
	}
	
	class Consumer extends Thread {
	    private Common common;
	    public Consumer(Common thisCommon){
	        common=thisCommon;
	    }
	    public void run(){
	        int i,production;
	        for(i=1;i<=20;i++){
	            production=common.get();
	            System.out.println("得到的数据是："+production);
	            try {
	                sleep(10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	class Producer extends Thread {
	    private Common common;
	    public Producer(Common thisCommon){
	        common=thisCommon;
	    }
	    public synchronized void run(){
	        for(int i=1;i<=10;i++){
	            common.put(i);
	            System.out.println("生产的数据是："+i);
	            try{
	                sleep(10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	}
	
	public static void main(String[] args){
        Common common=new ProducerAndConsumer().new Common();
        //两个生产者
        Producer producer1=new ProducerAndConsumer().new Producer(common);
        Producer producer2=new ProducerAndConsumer().new Producer(common);
        //一个消费者
        Consumer consumer=new ProducerAndConsumer().new Consumer(common);
        producer1.start();
        producer2.start();
        consumer.start();
    }

}
