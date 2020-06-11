package com.laz.knowledge.thirty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelfLog4jTest {
	private static Logger logger = LoggerFactory.getLogger(SelfLog4jTest.class);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			// 记录debug级别的信息
			logger.debug("This is debug message.");
			// 记录info级别的信息
			logger.info("This is info message.");
			// 记录error级别的信息
			logger.error("This is error message.");
			// 记录error级别的信息
			logger.error("This is error message,{}---{}.", "admin", "123");
		}
	}
}
