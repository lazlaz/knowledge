package com.laz.knowledge.sixtysix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class HashDosDemo {
	public static void main(String[] args) throws URISyntaxException {
		System.out.println(new HashDosDemo().index());
	}

	private String jsonStr = "";
	public String index() throws URISyntaxException {
		try {
			File file = new File(this.getClass().getResource("./javaHash.json").toURI());
			FileReader fr = new FileReader(file);// 需要读取的文件路径
			BufferedReader br = new BufferedReader(fr);
			jsonStr = br.readLine();
			br.close();
			fr.close(); // 关闭文件流
		} catch (IOException e) {
			System.out.println("指定文件不存在");// 处理异常
		}


		for (int i=0;i<20;i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Map<String, Object> map = new HashMap<String, Object>();
					map = JSON.parseObject(jsonStr, Map.class);//map中节点hash值一样，转换 成了红黑树
//					for (int i=0;i<60000;i++) {
//						map.put(i+"", i);
//					}
					//System.out.println(map);
				}
			}).start();
		}
	
		return "Hash Collision ~";
	}
}
