package com.laz.knowledge.thirtysix;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestMain {
	/**
	 * @param args
	 * @throws MalformedURLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		URL[] urls = new URL[1];
		urls[0] = new File("src/main/resources/thirtysix/").toURI().toURL();
		URLClassLoader url = new URLClassLoader(urls);
		String className = "com.laz.knowledge.thirtysix.A";
		Class c = url.loadClass(className);
		Method[] ms = c.getMethods();
		for (Method method : ms) {
			if (method.getName().contains("test")) {
				method.invoke(c.newInstance(), null);
			}
		}
	}
}
