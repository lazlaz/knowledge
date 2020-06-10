package com.laz.knowledge.twentyseven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainTest {
	 public static void main(String[] args) {
	        Connection connection = null;
	        Statement st = null;
	        ResultSet rs = null;

	        try {
	            //1.注册驱动
	            DriverManager.registerDriver(new com.xugu.cloudjdbc.Driver());

	            //2.建立连接
	            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
	            connection = DriverManager.getConnection("jdbc:xugu://172.18.194.154:5138/sms_server", "ROOT", "sms2020&");

	            //方法二
//	            DriverManager.getConnection("jdbc:msql://localhost/student?user=root&password=password");

	            //3.创建statement，跟数据库打交道一定需要这个对象
	            st = connection.createStatement();

	            //4.执行查询
	            String sql = "select * from sms_owner";
	            rs = st.executeQuery(sql);

	            //5.遍历查询每一条记录
	            while(rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                int age = rs.getInt("age");

	                System.out.println("id = " + id + "; name = " + name + "; age = " + age);
	            }
	             //进行资源释放
	            connection.close();
	            st.close();
	            rs.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
