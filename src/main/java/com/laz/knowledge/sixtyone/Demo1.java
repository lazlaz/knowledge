package com.laz.knowledge.sixtyone;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Demo1 {
	static class SyncProducer {
	    public static void main(String[] args) throws Exception {
	        //Instantiate with a producer group name.
	        DefaultMQProducer producer = new
	            DefaultMQProducer("please_rename_unique_group_name");
	        // Specify name server addresses.
	        producer.setNamesrvAddr("localhost:9876");
	        //Launch the instance.
	        producer.start();
	        for (int i = 0; i < 100; i++) {
	            //Create a message instance, specifying topic, tag and message body.
	            Message msg = new Message("TopicTest" /* Topic */,
	                "TagA" /* Tag */,
	                ("Hello RocketMQ " +
	                    i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
	            );
	            //Call send message to deliver message to one of brokers.
	            SendResult sendResult = producer.send(msg);
	            System.out.printf("%s%n", sendResult);
	        }
	        //Shut down once the producer instance is not longer in use.
	        producer.shutdown();
	    }
	}
	
	static class Consumer {

	    public static void main(String[] args) throws InterruptedException, MQClientException {

	        // Instantiate with specified consumer group name.
	        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");
	         
	        // Specify name server addresses.
	        consumer.setNamesrvAddr("localhost:9876");
	        
	        // Subscribe one more more topics to consume.
	        consumer.subscribe("TopicTest", "*");
	        // Register callback to execute on arrival of messages fetched from brokers.
	        consumer.registerMessageListener(new MessageListenerConcurrently() {

	            @Override
	            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
	                ConsumeConcurrentlyContext context) {
	                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
	                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	            }
	        });

	        //Launch the consumer instance.
	        consumer.start();

	        System.out.printf("Consumer Started.%n");
	    }
	}
}
