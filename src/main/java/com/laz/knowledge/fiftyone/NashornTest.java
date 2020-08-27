package com.laz.knowledge.fiftyone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.junit.Test;

public class NashornTest {
	// 执行js字符串
	@Test
	public void test1() {
		// 加载nashorn执行引擎
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			engine.eval("var str = 'Hello World';" + "print(str);");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	// 执行js字符串,从文件
	@Test
	public void test2() throws URISyntaxException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			FileReader scriptFile = new FileReader(new File(this.getClass().getResource("test.js").toURI()));
			engine.eval(scriptFile);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 传递参数给javascript代码
	@Test
	public void test3() throws URISyntaxException {
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			FileReader scriptFile = new FileReader(new File(this.getClass().getResource("test2.js").toURI()));
			SimpleBindings simpleBindings = new SimpleBindings();
			simpleBindings.put("name", "Nashorn");
			engine.eval(scriptFile, simpleBindings);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 指定调用的函数
	@Test
	public void test4() throws URISyntaxException {
		// 加载nashorn执行引擎
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			FileReader scriptFile = new FileReader(new File(this.getClass().getResource("RSA.js").toURI()));
			engine.eval(scriptFile);
			Invocable in = (Invocable) engine;
			Object str = in.invokeFunction("__convetPassword", "100200");
			System.out.println(str);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
