package com.laz.knowledge.fiftysix;

import com.laz.knowledge.fiftysix.DecoratorExample.Decorator;
import com.laz.knowledge.fiftysix.DecoratorExample.Source;
import com.laz.knowledge.fiftysix.DecoratorExample.Sourceable;

//代理模式
public class ProxyExample {
	public interface Sourceable {
		public void method();
	}
	
	public class Source implements Sourceable {

		@Override
		public void method() {
			System.out.println("the original method!");
		}
		
	}
	public class Proxy implements Sourceable {
		private Sourceable source;
		
		public Proxy(Sourceable source) {
			this.source = source;
		}
		@Override
		public void method() {
			before();
			source.method();
			after();
		}
		private void after() {
			System.out.println("after proxy!");
			
		}
		private void before() {
			System.out.println("before proxy!");
		}
		
	}
	public static void main(String[] args) {
		ProxyExample p = new ProxyExample();
		Sourceable s = p.new Source();
		Sourceable ds = p.new Proxy(s);
		ds.method();
	}
}
