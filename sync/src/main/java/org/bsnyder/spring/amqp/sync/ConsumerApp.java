package org.bsnyder.spring.amqp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsumerApp.class);

	public static void main(String[] args) {
		String receiveType = null;

		if (args.length > 0 && null != args[0] && !"".equals(args[0])) {
			receiveType = args[0];
		}

		ApplicationContext context = 
			new ClassPathXmlApplicationContext("/META-INF/spring/consumer-amqp-context.xml", ConsumerApp.class);
		SimpleMessageReceiver receiver = (SimpleMessageReceiver) context.getBean(SimpleMessageReceiver.class);
		LOG.debug("Using the '{}' receiveType", receiveType);

		for (int i = 0; i < 100; ++i) {
			receiver.receive(receiveType);
		}
	}

}
