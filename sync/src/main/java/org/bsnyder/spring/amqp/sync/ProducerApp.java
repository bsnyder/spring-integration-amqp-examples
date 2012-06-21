package org.bsnyder.spring.amqp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);
	
	public static void main(String[] args) {
		String sendType = null;

		if (args.length > 0 && null != args[0] && !"".equals(args[0])) {
			sendType = args[0];
		}

		ApplicationContext context = 
			new ClassPathXmlApplicationContext("/META-INF/spring/producer-amqp-context.xml", ProducerApp.class);
		SimpleMessageProducer producer = (SimpleMessageProducer) context.getBean("messageProducer");
		producer.sendMessages(sendType);
	}
}
