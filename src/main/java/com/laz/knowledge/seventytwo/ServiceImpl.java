package com.laz.knowledge.seventytwo;

import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class ServiceImpl implements Service {
	@Named("log")
	@Override
	public void sayHello() {
		System.out.println(String.format("[%s#%d] execute %s at %d", this.getClass().getSimpleName(), hashCode(),
				"sayHello", System.nanoTime()));
	}

}
