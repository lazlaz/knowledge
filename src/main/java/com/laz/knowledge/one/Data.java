package com.laz.knowledge.one;

/**
������Ӧ�ý����ݷ��鷢�͵�������
�����߲��ܴ������ݰ���ֱ�������߽����͵����ݰ��������
ͬ�������ͷ����ó��Է�����һ�����ݰ������ǽ��շ��Ѵ������һ�����ݰ�
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
