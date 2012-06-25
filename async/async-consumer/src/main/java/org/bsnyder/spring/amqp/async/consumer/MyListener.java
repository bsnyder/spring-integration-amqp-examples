package org.bsnyder.spring.amqp.async.consumer;

import org.bsnyder.spring.amqp.domain.Frisbee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.emc.consulting.blackhawk.core.domain.Project;

public class MyListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(MyListener.class);
	
//	public Project onMessage(Project project) {
	public Frisbee onMessage(Frisbee frisbee) {
//		if (null != project) {
		if (null != frisbee) {
//			LOG.info("###### Received project: {}", project);
			LOG.info("###### Received frisbee: {}", frisbee);
		}
		
//		project.setDescription("CHANGED");
//		LOG.info("###### Replying with project: {}", project);
//		return project;
		
		frisbee.appendToMessage(" World");
		LOG.info("###### Replying with frisbee: {}", frisbee);
		
		long messageNumber = frisbee.getMessageNumber();
		if (5 == messageNumber) {
			return null;
		}
		return frisbee;
	}
	
//	public String onMessage(String str) {
//		LOG.info("###### Received string: {}", str);
//		String str2 = "Hello World";
//		return str2;
//	}
}
