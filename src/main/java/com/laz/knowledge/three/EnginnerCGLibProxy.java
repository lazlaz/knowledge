package com.laz.knowledge.three;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EnginnerCGLibProxy {
	Object obj;

	public Object bind(final Object target) {
		this.obj = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(obj.getClass());
		enhancer.setCallback(new MyMethodInterceptor());
		return enhancer.create();
	}
	class MyMethodInterceptor implements MethodInterceptor{
		@Override
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("Enginner 2 writes document");
			//Object res = method.invoke(target, args);
			Object res =proxy.invokeSuper(obj, args);
			return res;
		}
	}
}
