package com.laz.knowledge.two;

import java.io.FileOutputStream;
import java.io.IOException;
import sun.misc.ProxyGenerator;

public class Main {
	/**
	 * 保存 JDK 动态代理生产的类
	 * 
	 * @param filePath 保存路径，默认在项目路径下生成 $Proxy0.class 文件
	 */
	private static void saveProxyFile(String... filePath) {
		if (filePath.length == 0) {
			System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		} else {
			FileOutputStream out = null;
			try {
				byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", ITester.class.getInterfaces());
				out = new FileOutputStream(filePath[0] + "$Proxy0.class");
				out.write(classFile);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
//		ProductOwner po = new ProductOwner("Ross");
//
//		ProductOwner poProxy = (ProductOwner) new EnginnerProxy().bind(po);
//
//		poProxy.defineBackLog();
//		saveProxyFile("D:/");
		Tester t = new Tester("boss");
		ITester r = (ITester) new EnginnerProxy().bind(t);
		r.doTesting();

	}

}
