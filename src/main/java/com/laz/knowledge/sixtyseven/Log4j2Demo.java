package com.laz.knowledge.sixtyseven;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.junit.Test;

public class Log4j2Demo {
	@Test
	public void test1() throws Exception {
		File file = new File(this.getClass().getResource("./log4j2.xml").toURI());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		final ConfigurationSource source = new ConfigurationSource(in);
		Configurator.initialize(null, source);

		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}

	@Test
	public void test2() throws Exception {
		File file = new File(this.getClass().getResource("./log4j2.xml").toURI());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		final ConfigurationSource source = new ConfigurationSource(in);
		Configurator.initialize(null, source);

		Logger logger = LogManager.getLogger("DEBUG");
		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}

	// 动态修改日志级别
	@Test
	public void test3() throws Exception {
		File file = new File(this.getClass().getResource("./log4j2.xml").toURI());
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		final ConfigurationSource source = new ConfigurationSource(in);
		Configurator.initialize(null, source);

		Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
		LoggerContext loggerContext = LoggerContext.getContext(false);
		LoggerConfig loggerConfig = loggerContext.getConfiguration().getRootLogger();
		Level level = Level.toLevel("debug");
		Configurator.setRootLevel(level);
		// 记录debug级别的信息
		logger.debug("This is debug message.");
		// 记录info级别的信息
		logger.info("This is info message.");
		// 记录error级别的信息
		logger.error("This is error message.");
	}
}
