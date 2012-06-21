package org.bsnyder.spring.amqp.async.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerApp.class);

	public static void main(String[] args) {
		AbstractApplicationContext context = 
			new ClassPathXmlApplicationContext("/META-INF/spring/consumer-context.xml", ConsumerApp.class);
		context.start();
		LOG.debug("###### Starting Spring application context");
	}

}
