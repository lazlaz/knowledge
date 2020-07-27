package com.laz.knowledge.fortysix;

public class StackOutOfMemoryDemo {
	public static void main(String[] args) throws Exception{
        new StackOutOfMemoryDemo().miao();
    }
 
    public void miao(){
        long time = System.currentTimeMillis();
        miao();
    }

}
