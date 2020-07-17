package com.laz.knowledge.one;

/**
发件人应该将数据分组发送到接收器
接受者不能处理数据包，直到发送者将发送的数据包发送完成
同样，发送方不得尝试发送另一个数据包，除非接收方已处理过上一个数据包
 * @author Administrator
 *
 */
public class Data {
	private String packet;
    
    // True if receiver should wait
    // False if sender should wait
    private boolean transfer = true;
  
    public synchronized void send(String packet) {
        while (!transfer) {
            try { 
            	System.out.println(Thread.currentThread().getName()+"wait");
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
        transfer = false;
        System.out.println(Thread.currentThread().getName());
        this.packet = packet;
        notifyAll();
    }
  
    public synchronized String receive() {
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e)  {
                Thread.currentThread().interrupt(); 
            }
        }
        transfer = true;
        System.out.println(Thread.currentThread().getName());
        notifyAll();
        return packet;
    }
}
