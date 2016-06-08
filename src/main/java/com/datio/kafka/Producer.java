package com.datio.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;



public class Producer {
	
	
	private static KafkaProducer<String, String> initialize() {
		
		System.out.println("loading properties..");		
		KafkaProducer<String, String> producer;	        
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("block.on.buffer.full", true);		   
		producer = new KafkaProducer<>(props);
		return producer;
	}
	
	public static void main(String args[]){		
		
	   KafkaProducer<String, String> producer = initialize();	   
	   
	   try {		   
		   for (int i = 0; i<100000; i++){
			   System.out.println("Sending message "+ i);
			   producer.send(new ProducerRecord<String, String>("test-1", "Message type one number "+i));
			   producer.send(new ProducerRecord<String, String>("test-2", "Message type two number "+i));
		   }
	   } 
	   catch (Exception e) {
		   System.err.println("Something went wrong while sending");
		   e.printStackTrace();
	   }
	   finally{
		   System.out.println("closing producer");
		   producer.close();		   
	   }	   	   
		
	}

}
