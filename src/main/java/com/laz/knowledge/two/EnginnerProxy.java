package com.laz.knowledge.two;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EnginnerProxy  implements InvocationHandler {
	Object obj;
	public Object bind(Object obj)
	{
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
		.getClass().getInterfaces(), this);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		System.out.println("Enginner writes document");
		Object res = method.invoke(proxy, args);
		return res;
	}

}
