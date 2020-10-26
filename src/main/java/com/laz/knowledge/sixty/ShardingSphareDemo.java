package com.laz.knowledge.sixty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class ShardingSphareDemo {
	@Test
	public void testInsert() throws SQLException {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
 
        DruidDataSource dataSource0 = new DruidDataSource();
        dataSource0.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource0.setUrl("jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding" +
                "=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true");
        dataSource0.setUsername("root");
        dataSource0.setPassword("root");
        
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource1.setUrl("jdbc:mysql://localhost:3306/test2?autoReconnect=true&useUnicode=true&characterEncoding" +
                "=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("database0", dataSource0);
        dataSourceMap.put("database1", dataSource1);
        //数据库表分库分表规则
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration("t_order");
        //根据字段进行分库
        tableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id",
                "database${user_id % 2}"));
        //根据字段进行分表
        tableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id",
                "t_order_${order_id % 2}"));
        //自定义复杂分表设置 MyComplexShardingAlgorithm自定义分表算法
//        tableRuleConfiguration.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration("regdate",
//                new MyComplexShardingAlgorithm()));
 
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        shardingRuleConfiguration.getTableRuleConfigs().add(tableRuleConfiguration);
 
        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration,
                new Properties());
        String sql = "insert into t_order (user_id,order_id,regdate) values (?, ?, ?)";
        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        Connection connection = dataSource.getConnection();
        for (int i=0;i<10;i++) {
        	PreparedStatement preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setInt(1, i);
        	preparedStatement.setInt(2, i);
        	preparedStatement.setDate(3, date);
        	preparedStatement.execute();
        }
        connection.close();
    }
	
	@Test
	public void testQuery() throws SQLException {
		 Map<String, DataSource> dataSourceMap = new HashMap<>();
		 
	        DruidDataSource dataSource0 = new DruidDataSource();
	        dataSource0.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource0.setUrl("jdbc:mysql://localhost:3306/test?autoReconnect=true&useUnicode=true&characterEncoding" +
	                "=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true");
	        dataSource0.setUsername("root");
	        dataSource0.setPassword("root");
	        
	        DruidDataSource dataSource1 = new DruidDataSource();
	        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource1.setUrl("jdbc:mysql://localhost:3306/test2?autoReconnect=true&useUnicode=true&characterEncoding" +
	                "=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true");
	        dataSource1.setUsername("root");
	        dataSource1.setPassword("root");
	        dataSourceMap.put("database0", dataSource0);
	        dataSourceMap.put("database1", dataSource1);
	        
	        // 配置Order表规则
	        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","database${0..1}.t_order_${0..1}");
	        
	        // 配置分库 + 分表策略
	        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "database${user_id % 2}"));
	        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
	        
	        // 配置分片规则
	        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
	        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
	        
	        DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig,
	                new Properties());
	        String sql = "select user_id from t_order";
	        Connection connection = dataSource.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        ResultSet rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	System.out.println("user_id:"+rs.getInt(1));
	        }
	        connection.close();
	        
	}

}
