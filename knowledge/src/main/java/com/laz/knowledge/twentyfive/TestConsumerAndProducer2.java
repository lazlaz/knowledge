package com.laz.knowledge.twentyfive;

public class TestConsumerAndProducer2 {
	 public static void main(String[] args) {
	        Resource2 resource = new Resource2();
	        //生产线程
	        Producer2 p1 = new Producer2(resource);
	        //消费线程
	        Consumer2 c1 = new Consumer2(resource);
	 
	        new Thread(p1).start();
	 
	        new Thread(c1).start();
	    }
}
