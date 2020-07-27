package com.laz.knowledge.fortyfive;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class SoftRefTest {

	public static void main(String[] args) {
		SoftReference<Obj> softReference = new SoftReference<>(new Obj());

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
	}

}
