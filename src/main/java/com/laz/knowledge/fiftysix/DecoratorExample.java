package com.laz.knowledge.fiftysix;

//装饰模式
public class DecoratorExample {
	public interface Sourceable {
		public void method();
	}
	
	public class Source implements Sourceable {

		@Override
		public void method() {
			System.out.println("the original method!");
		}
		
	}
	
	public class Decorator implements Sourceable {
		private Sourceable source;
		
		public Decorator(Sourceable source) {
			this.source = source;
		}
		@Override
		public void method() {
			System.out.println("before decorator!");
			source.method();
			System.out.println("after decorator!");
		}
		
	}
	public static void main(String[] args) {
		DecoratorExample d = new DecoratorExample();
		Sourceable s = d.new Source();
		Sourceable ds = d.new Decorator(s);
		ds.method();
	}
}
