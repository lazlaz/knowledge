package com.laz.knowledge.seven;

import java.util.ArrayList;
import java.util.List;

public class HeapTest {
	public static void main(String[] args) {
		List<String> aList = new ArrayList<String>();
		try{
			while(true){
				aList.add("asdasdasdas");
			}
		}catch(Throwable e){
			System.out.println(aList.size());
			e.printStackTrace();
		}
	}

}
