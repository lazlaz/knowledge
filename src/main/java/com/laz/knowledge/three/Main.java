package com.laz.knowledge.three;

import com.laz.knowledge.two.Tester;

import net.sf.cglib.core.DebuggingClassWriter;

public class Main {
	public static void main(String[] args) {
		 //在指定目录下生成动态代理类，我们可以反编译看一下里面到底是一些什么东西
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\java\\java_workapace");
		Tester t = new Tester("boss");
		Tester t2 = new Tester("boss");
		Tester rossProxy = (Tester) new EnginnerCGLibProxy().bind(t);
		System.out.println(System.identityHashCode(rossProxy));
		rossProxy.doTest2();
	}
}
