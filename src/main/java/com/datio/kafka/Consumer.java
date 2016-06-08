package com.datio.kafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {
	
	private static KafkaConsumer<String, String> initialize() {
		
		KafkaConsumer<String, String> consumer;
		Properties props = new Properties();
        props.put("enable.auto.commit", true);	
        props.put("receive.buffer.bytes", 262144);	
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test");
        props.put("session.timeout.ms", 10000);
        props.put("bootstrap.servers", "localhost:9092");
        props.put("max.partition.fetch.bytes", 2097152);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("fetch.min.bytes", 50000);
        
        if (props.getProperty("group.id") == null) {
        	props.setProperty("group.id", "group-" + new Random().nextInt(100000));
        }        
        
        consumer = new KafkaConsumer<>(props);
		return consumer;
	}
	
	
	public static void main (String args[]){
			
		 KafkaConsumer<String, String> consumer;
		 
        consumer = initialize();
        
        int timeouts = 0;
        
        consumer.subscribe(Arrays.asList("test-1", "test-2"));
        while (true) {        	
        	ConsumerRecords<String, String> records = consumer.poll(50); // polling each 50 ms
            
			if (records.count() == 0) {
                timeouts++;
            } else {
                //System.out.printf("Got this after %d timeouts\n", timeouts);
                
                for (ConsumerRecord<String, String> record : records) {
                	System.out.printf("\nGot this after %d timeouts: %s", timeouts, record.value());
                }
                
                timeouts = 0;
            }        	
        }
	}



}
