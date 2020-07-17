package com.laz.knowledge.twentyseven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	            connection = DriverManager.getConnection("jdbc:xugu://172.18.194.154:5138/sms_server", "sms_server", "sms2020&");

	            //方法二
//	            DriverManager.getConnection("jdbc:msql://localhost/student?user=root&password=password");

	            //3.创建statement，跟数据库打交道一定需要这个对象
	            st = connection.createStatement();
	            PreparedStatement ps = null;
	            int n = 1000;
	            while (n>0) {
	            	try {
	            		//4.执行查询
	            		String sql = "select sms_monit_conf_kpi_id from sms_monit_conf_kpi limit 0,100";
	            		rs = st.executeQuery(sql);
	            		int count = 0;
	            		String delSql = "delete  from sms_monit_conf_kpi where sms_monit_conf_kpi_id=?";
	            		ps = connection.prepareStatement(delSql);//获取PrepareStatement
	            		//5.遍历查询每一条记录
	            		while(rs.next()) {
	            			System.out.println(count++);
	            			String id = rs.getString("sms_monit_conf_kpi_id");
	            			ps.setString(1,id);//填充占位符
	            			ps.addBatch();//添加到批处理方法中，调用无参的方法，有参的是Statement来调用的！
	            		}
	            		ps.executeBatch();//执行批处理
	            		n--;
	            	}catch(Exception e) {
	            		e.printStackTrace();
	            	}
	            }
	             //进行资源释放
	            connection.close();
	            st.close();
	            rs.close();
	            ps.close();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
