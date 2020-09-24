package com.laz.knowledge.twentyseven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import com.sybase.jdbc3.jdbc.SybDriver;


public class JDBCConnectionDemo {
	
	@Test
	public void testMysql() {
		Connection connection = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xxl_job?useSSL=false", "root", "root");

            String sql = "UPDATE xxl_job_log set handle_time=? where id=1";
            PreparedStatement ps = connection.prepareStatement(sql);
            Timestamp t = new Timestamp(new Date().getTime());
            ps.setTimestamp(1, t);
            ps.executeUpdate();
             //进行资源释放
            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Test
	public void testMysql2() {
		Connection connection = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xxl_job?useSSL=false", "root", "root");
            String sql = "UPDATE xxl_job_log set handle_time='2012-08-11 11:11:11' where id=1";
            Statement st = connection.createStatement();
            st.executeUpdate(sql);
             //进行资源释放
            connection.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Test //statement sql注入示例
	public void testMysql3() {
		Connection connection = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xxl_job?useSSL=false", "root", "root");
            String str = "'' or 1 = 1";
            String sql = "select * from xxl_job_log where id="+str;
            
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString("trigger_msg");
                System.out.println("name = " + name);
            }
             //进行资源释放
            connection.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Test //preperstatement防sql注入示例(底层''这些特殊符号被转义了)
	public void testMysql4() {
		Connection connection = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/xxl_job?useSSL=false", "root", "root");
            String str = "'' or 1 = 1";
            String sql = "select * from xxl_job_log where id=?";
            
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, str);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("trigger_msg");
                System.out.println("name = " + name);
            }
             //进行资源释放
            connection.close();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	@Test
	public void testXugu() {
		Connection connection = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.xugu.cloudjdbc.Driver());

            //2.建立连接
            //方法一  参数一：协议+访问数据库，参数二：用户名，参数三：密码
            connection = DriverManager.getConnection("jdbc:xugu://172.18.194.154:5138/sms_server", "sms_server", "sms2020&");

            String sql = "UPDATE xxl_job_log set handle_time=? where id=4948";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setTimestamp(1, new Timestamp(new Date().getTime()));
            ps.executeUpdate();
             //进行资源释放
            connection.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void testSybase() {
		   Connection conn;
		   try 
		   {
		      Class.forName("com.sybase.jdbc3.jdbc.SybDriver");
		      System.out.println("Driver Loaded");
		      String url = "jdbc:sybase:Tds:192.168.5.45:2638";
		      conn = DriverManager.getConnection(url,"dba","123456");
		      Statement stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from zxdbl_888..account_0");
		      rs.next();
		      System.out.println(rs.getString(2));
		   } catch(Exception e) {
			   e.printStackTrace();
		   }
	}
}
