package org.bsnyder.spring.amqp.async.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emc.consulting.blackhawk.core.domain.Project;

public class ProducerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);
	
	public static void main(String[] args) {
		AbstractApplicationContext context = 
			new ClassPathXmlApplicationContext("/META-INF/spring/producer-context.xml", ProducerApp.class);
		MyProducer producer = (MyProducer) context.getBean("producer");
		
		Frisbee frisbee = null;
		for (int i = 0; i < 10; ++i) {
            frisbee = new Frisbee(i, "Hello");
//            frisbee.setMessageNumber(i);
//            frisbee.appendToMessage("Hello");
            LOG.debug("###### Sending frisbee: {}", frisbee);
			  producer.send(frisbee);
			  LOG.debug("###### After the send has occurred");
//			Project project = new Project();
//			project.setId("" + i);
//			project.setName("fooProject");
//			project.setKey("fooKey");
//			project.setClient("fooClient");
//			LOG.debug("###### Sending project: {}", project);
//			producer.send(project);
//			LOG.debug("###### After the send has occurred");
		}
		
		context.close();
		LOG.debug("###### Done");
	}
}
