package com.laz.knowledge.fortynine;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MangoDBTest {
	@Test
	public void testConnection() {
		getConnect();
	}
	//插入一个文档
	@Test
	public void insertOneTest(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase =getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //要插入的数据
	    Document document = new Document("name","张三")
	                            .append("sex", "男")
	                            .append("age", 18);
	    //插入一个文档
	    collection.insertOne(document);
	}
	//查找集合中的所有文档
	@Test
	public void findTest(){
	    //获取数据库连接对象
	    MongoDatabase mongoDatabase = getConnect();
	    //获取集合
	    MongoCollection<Document> collection = mongoDatabase.getCollection("user");
	    //查找集合中的所有文档
	    FindIterable findIterable = collection.find();
	    MongoCursor cursor = findIterable.iterator();
	    while (cursor.hasNext()) {
	        System.out.println(cursor.next());
	    }
	}
	//需要密码认证方式连接
    public  MongoDatabase getConnect(){
        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("172.18.130.44", 27017);
        adds.add(serverAddress);
        
        List<MongoCredential> credentials = new ArrayList<>();
//        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
//        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
//        credentials.add(mongoCredential);
//        
        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);
 
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
 
        //返回连接数据库对象
        return mongoDatabase;
    }

}
