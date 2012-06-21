package org.bsnyder.spring.amqp.async.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.emc.consulting.blackhawk.core.domain.Project;

public class MyListener {
	
	private static final Logger LOG = LoggerFactory.getLogger(MyListener.class);
	
//	public void onMessage(byte[] msg) {
//	public Project onMessage(Project project) {
	public Frisbee onMessage(Frisbee frisbee) {
//		if (null != project) {
		if (null != frisbee) {
//		if (null != msg) {
//			String jsonMsg = new String(msg);
//			LOG.info("###### Received project: {}", msg);
//			LOG.info("###### Received project: {}", project);
			LOG.info("###### Received frisbee: {}", frisbee);
		}
//		LOG.info("###### Received message: {}", msg);
//		LOG.info("Received frisbee number: {} with message: {}", frisbee.getMessageNumber(), frisbee.getMessage());
//		project.setDescription("CHANGED");
//		LOG.info("###### Replying with project: {}", project);
//		return project;
		frisbee.appendToMessage("World");
		LOG.info("###### Replying with frisbee: {}", frisbee);
		return frisbee;
	}
}
