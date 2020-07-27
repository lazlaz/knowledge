package com.laz.knowledge.fortysix;

import java.util.ArrayList;
import java.util.List;

//-Xms10m -Xmx10m -XX:MaxTenuringThreshold
public class YoungOutOfMemoryDemo {
	public static void main(String[] args) {
		 ArrayList<String> strs = new ArrayList<>(10000_0000);
	        for(int i = 0 ;i <= 10000_0000; ++ i){
	            strs.add(Integer.toString(i));
	            if(i % 10000 == 0){
	                System.out.println(i);
	            }
	        }
	}
}
