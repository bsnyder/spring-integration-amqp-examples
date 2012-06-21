package org.bsnyder.spring.amqp.async.producer;

import java.util.concurrent.Future;

import com.emc.consulting.blackhawk.core.domain.Project;


public interface MyProducer {
	
//	public Frisbee send(Frisbee frisbee);
	public Future<Project> send(Project project);

}
