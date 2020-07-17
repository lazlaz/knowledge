package com.laz.knowledge.twentythree;

import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;

public class SVNKitDemo {
	@Test
	public void test1() {
		SVNKit kit = new SVNKit();
		try {
			boolean b= kit.addDir("sms", "smspass", "svn://172.18.194.117/", "/home");
			System.out.println(b);
		} catch (SVNException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		SVNKit kit = new SVNKit();
		try {
			boolean b= kit.checkPath("sms", "smspass", "svn://172.18.194.117/", "/soft");
			System.out.println(b);
		} catch (SVNException e) {
			e.printStackTrace();
		}
	}
}
