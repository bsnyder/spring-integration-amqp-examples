package org.bsnyder.spring.amqp.async.producer;

import java.util.concurrent.Future;

import org.bsnyder.spring.amqp.domain.Frisbee;

//import com.emc.consulting.blackhawk.core.domain.Project;


public interface MyProducer {
	
	public Future<Frisbee> send(Frisbee frisbee);
//	public Future<String> send(String str);
//	public Future<Project> send(Project project);

}
