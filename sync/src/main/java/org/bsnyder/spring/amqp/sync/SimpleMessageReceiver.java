package org.bsnyder.spring.amqp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SimpleMessageReceiver {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageReceiver.class);
	
	protected RabbitTemplate rabbitTemplate;
	
	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void receive(String receiveType) {
		if ("amqpReceive".equalsIgnoreCase(receiveType)) {
			amqpReceive();
		} else if ("receiveAndConvert".equalsIgnoreCase(receiveType)) {
			receiveAndConvert();
		}
	}
	
	public void amqpReceive() {
		Message message = rabbitTemplate.receive("TEST.FOO");
		LOG.debug("Received an AMQP message: {}", message);
	}
	
	public void receiveAndConvert() {
		String message = (String) rabbitTemplate.receiveAndConvert();
		LOG.debug("Received an AMQP message: {}", message);
	}

}
