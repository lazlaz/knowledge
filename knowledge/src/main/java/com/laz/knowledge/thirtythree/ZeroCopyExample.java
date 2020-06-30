package com.laz.knowledge.thirtythree;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Java NIO Transfer零拷贝与普通复制测试对比
 */
public class ZeroCopyExample {
	public static void main(String[] args) throws IOException {
		int t = Integer.valueOf(args[0]);
		if (t == 0) {
			buildData();
		} else if (t == 1) {
			test(true, 1);
			System.out.println("-----------");
			test(true, 1);
		} else if (t == 2) {
			test(false, 1);
			System.out.println("-----------");
			test(false, 1);
		}
	}

	public static void test(boolean old, int num) {
		IntStream.range(0, num).forEachOrdered(i -> {
			Arrays.stream(new String[] { "t1k.txt", "t1M.txt", "t100M.txt", "t500M.txt", "t1G.txt" }).forEach(src -> {
				long s = System.currentTimeMillis();
				if (old) {
					oldCopy(new File("d:/tmp/", src), new File("d:/tmp/test", src + ".oldCopy.txt"));
				} else {
					zeroCopy(new File("d:/tmp/", src), new File("d:/tmp/test", src + ".copy.txt"));
				}
				long e = System.currentTimeMillis();
				System.out.printf("%s,%s,%s\n", old, src, e - s+"ms");
			});
			;
		});
	}
	public static void zeroCopy(File src,File dst) {
		try (
			FileChannel in = FileChannel.open(src.toPath(), StandardOpenOption.READ);
			FileChannel out = FileChannel.open(dst.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		){
			in.transferTo(0, in.size(), out);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void oldCopy(File src, File dst) {
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dst))) {
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = in.read(data)) > 0) {
				out.write(data, 0, len);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void buildData() throws IOException {
		mkDataFile(new File("d:/tmp/t1k.txt"), 1024 * 1);
		mkDataFile(new File("d:/tmp/t1M.txt"), 1024 * 1024 * 1);
		mkDataFile(new File("d:/tmp/t100M.txt"), 1024 * 1024 * 100);
		mkDataFile(new File("d:/tmp/t500M.txt"), 1024 * 1024 * 500);
		mkDataFile(new File("d:/tmp/t1G.txt"), 1024 * 1024 * 1024);
	}

	/**
	 * 生成指定大小数据的文件
	 *
	 * @param file  文件
	 * @param bytes 字节数
	 */
	public static void mkDataFile(File file, long bytes) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1);
		for (int i = 0; i < buffer.capacity(); i++) {
			byte v = (byte) ('A' + i % 26);
			buffer.put(v);
		}
		buffer.flip();
		try (FileChannel fileChannel = FileChannel.open(file.toPath(), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE)) {
			for (int i = 0; i < bytes / buffer.capacity(); i++) {
				fileChannel.write(buffer);
				buffer.clear();
			}
		} catch (IOException e) {
			throw e;
		}
	}
}
