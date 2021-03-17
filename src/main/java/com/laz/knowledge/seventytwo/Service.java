package com.laz.knowledge.seventytwo;

import com.google.inject.ImplementedBy;

@ImplementedBy(ServiceImpl.class)
public interface Service {
	public void sayHello();
}
