package com.laz.knowledge.fortysix;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

//-XX:MaxDirectMemorySize=12m -XX:+DisableExplicitGC
public class DirectMemoryOutOfMemoryDemo {
	public static void main(String[] args) throws Exception {
		List<ByteBuffer> buffers = new ArrayList<>();
		while (true) {
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 1024);
			//buffers.add(buffer);
		}
	}

}
