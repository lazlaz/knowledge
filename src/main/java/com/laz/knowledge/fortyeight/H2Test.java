package com.laz.knowledge.fortyeight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class H2Test {
	private static final String JDBC_URL = "jdbc:h2:tcp://172.18.194.231:9092//opt/.h2/kara;LOCK_TIMEOUT=10000;CACHE_SIZE=8192;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9090;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
	// 连接数据库时使用的用户名
	private static final String USER = "sa";
	// 连接数据库时使用的密码
	private static final String PASSWORD = "123456";
	// 连接H2数据库时使用的驱动类，org.h2.Driver这个类是由H2数据库自己提供的，在H2数据库的jar包中可以找到
	private static final String DRIVER_CLASS = "org.h2.Driver";

	@Test
	public void test() throws SQLException, ClassNotFoundException {
		// 加载H2数据库驱动
		Class.forName(DRIVER_CLASS);
		// 根据连接URL，用户名，密码获取数据库连接
		Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
		Statement stmt = conn.createStatement();
		// 查询
//		ResultSet rs = stmt.executeQuery("SELECT * FROM host_pkg");
//		// 遍历结果集
//		while (rs.next()) {
//			System.out.println(rs.getString("status"));
//		}
		stmt.executeUpdate("UPDATE host_pkg SET status='1' WHERE basedir='/opt/disconf'");
		// 释放资源
		stmt.close();
		// 关闭连接
		conn.close();

	}
}
