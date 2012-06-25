package org.bsnyder.spring.amqp.async.producer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.bsnyder.spring.amqp.domain.Frisbee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.emc.consulting.blackhawk.core.domain.Project;

public class ProducerApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);
	
	private static Map<Integer, Frisbee> requests = new HashMap<Integer, Frisbee>();
//	  private static Map<String,Future<String>> replies = new HashMap<String, Future<String>>();
	private static Map<String,Future<Frisbee>> replies = new HashMap<String, Future<Frisbee>>();
	private static ExecutorService executor = Executors.newFixedThreadPool(100);
		
	public static void main(String[] args) throws Exception {
		AbstractApplicationContext context = 
			new ClassPathXmlApplicationContext("/META-INF/spring/producer-context.xml", ProducerApp.class);
		MyProducer producer = (MyProducer) context.getBean("producer");
		
		Frisbee frisbee = null;
		
		// Send everything and stuff the replies in a map
		for (int i = 0; i < 10; ++i) {
//			String request = "Hello" + i;
//			LOG.info("###### Sending string: {}", request);
//			Future<String> reply = producer.send(request);
//			replies.put("reply" + i, reply);
			
            frisbee = new Frisbee(i, "Hello");
            requests.put(i, frisbee);
            Future<Frisbee> reply = producer.send(frisbee);
			replies.put("reply" + i, reply);
			
//			Project project = new Project();
//			project.setId("" + i);
//			project.setName("fooProject");
//			project.setKey("fooKey");
//			project.setClient("fooClient");
//			LOG.debug("###### Sending project: {}", project);
//			producer.send(project);
//			LOG.debug("###### After the send has occurred");
		}
		
		// Walk the map of replies to process them 
//		for (final Map.Entry<String, Future<String>> entry : replies.entrySet()) {
		for (final Map.Entry<String, Future<Frisbee>> entry : replies.entrySet()) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
//					String result = doProcessEntry(entry);
					doProcessEntry(entry);
				}
				
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(20, TimeUnit.SECONDS);
		LOG.info("###### Completed");
		System.exit(0);
	}
	
//	private static String doProcessEntry(Map.Entry<String, Future<Frisbee>> entry) {
	private static void doProcessEntry(Map.Entry<String, Future<Frisbee>> entry) {
		String key = entry.getKey();
		Future<Frisbee> value = entry.getValue();
		try {
//			String result = value.get(20, TimeUnit.SECONDS);
			Frisbee result = value.get(10, TimeUnit.SECONDS);
			LOG.info("###### Received frisbee: {}", result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
