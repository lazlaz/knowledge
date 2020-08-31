package com.laz.knowledge.fiftythree;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义 Java类加载器来实现Java 类的热加载
 *
 */
public class MyClasslLoader extends ClassLoader {

	/** 要加载的 Java 类的 classpath 路径 */
	private String classpath;

	public MyClasslLoader(String classpath) {
		// 指定父加载器
		super(ClassLoader.getSystemClassLoader());
		this.classpath = classpath;
	}
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		if (name.startsWith("com.laz.knowledge.fiftythree.MyManager")) {
			byte[] data = this.loadClassData(name);
			return this.defineClass(name, data, 0, data.length);
		}
		return super.loadClass(name);
	}	

	/**
	 * 加载 class 文件中的内容
	 *
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		try {
			// 传进来是带包名的
			name = name.replace(".", "//");
			FileInputStream inputStream = new FileInputStream(new File(classpath + name + ".class"));
			// 定义字节数组输出流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b = 0;
			while ((b = inputStream.read()) != -1) {
				baos.write(b);
			}
			inputStream.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
