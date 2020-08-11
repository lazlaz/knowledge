package com.laz.knowledge.fortyeight;

import java.util.Collection;

import org.junit.Test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ColumnDefinitions.Definition;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

public class CassandraTest {
	@Test
	public void testMeta() {
		connect();
		Metadata metadata = cluster.getMetadata();
		for (Host host : metadata.getAllHosts()) {
			System.out.println("------" + host.getAddress());
		}

		System.out.println("======================");

		for (KeyspaceMetadata keyspaceMetadata : metadata.getKeyspaces()) {
			System.out.println("--------" + keyspaceMetadata.getName());
			Collection<TableMetadata> c = keyspaceMetadata.getTables();
			for (TableMetadata t : c) {
				System.out.println(keyspaceMetadata.getName()+":"+t.getName());
			}
		}

	}

	@Test
	public void testCreateKeySpace() {
		connect();
		createKeyspace();
	}
	
	@Test
	public void testCreateTable() {
		connect();
		createTable();
	}

	@Test
	public void testInsert() {
		connect();
		insert();
	}

	Cluster cluster;
	Session session;

	public void connect() {
		// addContactPoints:cassandra节点ip withPort:cassandra节点端口 默认9042
		// withCredentials:cassandra用户名密码
		// 如果cassandra.yaml里authenticator：AllowAllAuthenticator 可以不用配置
		cluster = Cluster.builder().addContactPoints("172.18.194.158").withPort(9042).build();
		session = cluster.connect();
	}

	/**
	 * 创建键空间
	 */
	public void createKeyspace() {
		// 单数据中心 复制策略 ：1
		String cql = "CREATE KEYSPACE if not exists mydb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}";
		session.execute(cql);
	}

	/**
	 * 创建表
	 */
	public void createTable() {
		// a,b为复合主键 a：分区键，b：集群键
		String cql = "CREATE TABLE if not exists mydb.test (a text,b int,c text,d int,PRIMARY KEY (a, b))";
		session.execute(cql);
	}

	/**
	 * 插入
	 */
	public void insert() {
		String cql = "INSERT INTO mydb.test (a , b , c , d ) VALUES ( 'a2',4,'c2',6);";
		session.execute(cql);
	}

	/**
	 * 修改
	 */
	public void update() {
		// a,b是复合主键 所以条件都要带上，少一个都会报错，而且update不能修改主键的值，这应该和cassandra的存储方式有关
		String cql = "UPDATE mydb.test SET d = 1234 WHERE a='aa' and b=2;";
		// 也可以这样 cassandra插入的数据如果主键已经存在，其实就是更新操作
		String cql2 = "INSERT INTO mydb.test (a,b,d) VALUES ( 'aa',2,1234);";
		// cql 和 cql2 的执行效果其实是一样的
		session.execute(cql);
	}

	/**
	 * 删除
	 */
	public void delete() {
		// 删除一条记录里的单个字段 只能删除非主键，且要带上主键条件
		String cql = "DELETE d FROM mydb.test WHERE a='aa' AND b=2;";
		// 删除一张表里的一条或多条记录 条件里必须带上分区键
		String cql2 = "DELETE FROM mydb.test WHERE a='aa';";
		session.execute(cql);
		session.execute(cql2);
	}

	/**
	 * 查询
	 */
	public void query() {
		String cql = "SELECT * FROM mydb.test;";
		String cql2 = "SELECT a,b,c,d FROM mydb.test;";

		ResultSet resultSet = session.execute(cql);
		System.out.print("这里是字段名：");
		for (Definition definition : resultSet.getColumnDefinitions()) {
			System.out.print(definition.getName() + " ");
		}
		System.out.println();
		System.out.println(String.format("%s\t%s\t%s\t%s\t\n%s", "a", "b", "c", "d",
				"--------------------------------------------------------------------------"));
		for (Row row : resultSet) {
			System.out.println(String.format("%s\t%d\t%s\t%d\t", row.getString("a"), row.getInt("b"),
					row.getString("c"), row.getInt("d")));
		}
	}
}
