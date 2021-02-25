package com.laz.knowledge.seventyone;

public class LambdaApp {
	public static void sayHello(String name, Greet greet) {
		greet.say(name);
	}

	public static void main(String[] args) {
		//只要是函数式接口形式度可以赋值
		Greet g = (x) -> {
			System.out.println("Hello " + x);
		};
		LambdaApp.sayHello("tqz", g);
	}
}
