# 对于一个类有子静态类，在外部调用子静态类方法，不会出入该类的static

```		
//在其他代码中调用 new A.B()，不会触发A的static执行	
public class A {
	static {
		System.out.println("AAA");
	}
	
	public static class B {
		public B() {
			System.out.println("B");
		}
	}
}
```