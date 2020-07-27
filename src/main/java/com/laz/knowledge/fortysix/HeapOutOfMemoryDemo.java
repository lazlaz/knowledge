package com.laz.knowledge.fortysix;

import java.util.ArrayList;
import java.util.List;

//-Xms10m -Xmx10m
public class HeapOutOfMemoryDemo {
	public static void main(String[] args) {
		List list = new ArrayList();
		for (int i=0;i<10;i++) {
			byte[] b = new byte[1024*1024];
			list.add(b);
		}
	}
}
