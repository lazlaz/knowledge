package com.laz.knowledge.eleven;

import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringAOP.xml");

		Subject subject1 = (Subject) ctx.getBean("SubjectImpl1");
		Subject subject2 = (Subject) ctx.getBean("SubjectImpl2");

		subject1.login();
		subject1.download();

		System.err.println("==================");

		subject1.login();
		subject1.download();

	}
}
