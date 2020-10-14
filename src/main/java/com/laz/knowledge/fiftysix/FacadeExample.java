package com.laz.knowledge.fiftysix;

//外观模式
public class FacadeExample {
	public class ModuleA {
		// 示意方法
		public void testA() {
			System.out.println("调用ModuleA中的testA方法");
		}
	}

	public class ModuleB {
		// 示意方法
		public void testB() {
			System.out.println("调用ModuleB中的testB方法");
		}
	}

	public class ModuleC {
		// 示意方法
		public void testC() {
			System.out.println("调用ModuleC中的testC方法");
		}
	}

	public class Facade {
		// 示意方法，满足客户端需要的功能
		public void test() {
			ModuleA a = new ModuleA();
			a.testA();
			ModuleB b = new ModuleB();
			b.testB();
			ModuleC c = new ModuleC();
			c.testC();
		}
	}

	public static void main(String[] args) {
		Facade facade = new FacadeExample().new Facade();
		facade.test();
	}

}
