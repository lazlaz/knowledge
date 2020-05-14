package com.laz.knowledge.seventeen;

import java.util.ServiceLoader;

public class SPIMain {
	public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
