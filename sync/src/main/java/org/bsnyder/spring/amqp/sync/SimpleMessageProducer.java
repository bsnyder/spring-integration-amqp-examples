package org.bsnyder.spring.amqp.sync;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class SimpleMessageProducer {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageProducer.class);
	
	protected RabbitAdmin rabbitAdmin;
	
	protected RabbitTemplate rabbitTemplate;
	
	protected int numberOfMessages = 100;
	
	public RabbitAdmin getRabbitAdmin() {
		return rabbitAdmin;
	}

	public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
		this.rabbitAdmin = rabbitAdmin;
	}

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessages(String sendType) {
		
		rabbitAdmin.declareQueue(new Queue("TEST.FOO"));
		
		if ("amqpSend".equalsIgnoreCase(sendType)) {
			amqpSendMessages();
		} else if ("convertAndSend".equalsIgnoreCase(sendType)) {
			convertAndSendMessages();
		}
	}
	
	public void convertAndSendMessages() {
        final StringBuilder buffer = new StringBuilder(); 
        
        for (int i = 0; i < numberOfMessages; ++i) {
            buffer.append("Message '").append(i).append("' sent at: ").append(new Date());
            rabbitTemplate.convertAndSend(buffer.toString());
        }
    }
	
	public void amqpSendMessages() {
        final StringBuilder buffer = new StringBuilder(); 
        
		for (int i = 0; i < numberOfMessages; ++i) {
            buffer.append("Message '").append(i).append("' sent at: ").append(new Date());
            
            Message message = new Message(buffer.toString().getBytes(), new MessageProperties());
            
            rabbitTemplate.send("TEST.FOO", "TEST.FOO", message);
        }
	}

}
