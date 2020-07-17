package com.laz.knowledge.eleven;

//切面
public class PermissionVerification {
	/**
	 * 权限校验
	 * 
	 * @param args 登陆参数
	 */
	public void canLogin() {

		// 做一些登陆校验
		System.err.println("我正在校验啦！！！！");

	}

	/**
	 * 校验之后做一些处理（无论是否成功都做处理）
	 * 
	 * @param args 权限校验参数
	 */
	public void saveMessage() {

		// 做一些后置处理
		System.err.println("我正在处理啦！！！！");
	}

}
