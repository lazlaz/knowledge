package com.laz.knowledge.fortyfive;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

//-Xms10m -Xmx10m
public class SoftReferenceQueueTest {
	public static void main(String[] args) {
		ReferenceQueue<Obj> queue = new ReferenceQueue<>();
		SoftReference<Obj> softReference = new SoftReference<>(new Obj(), queue);
		List<Obj> list = new ArrayList<>();
		while (true) {
			if (softReference.get() != null) {
				list.add(new Obj());
				System.out.println("list.add");
			} else {
				System.out.println("---------软引用已被回收---------");
				break;
			}
			System.gc();
		}
		Reference<? extends Obj> pollRef = queue.poll();
		while (pollRef != null) {
			System.out.println(pollRef);
			System.out.println(pollRef.get());
			pollRef = queue.poll();
		}

	}
}
