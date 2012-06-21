package org.bsnyder.spring.amqp.async.producer.util;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class FrisbeeToMessageConverter extends AbstractMessageConverter {

	@Override
	protected org.springframework.amqp.core.Message createMessage(Object object, MessageProperties messageProperties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object fromMessage(org.springframework.amqp.core.Message message) throws MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}


}
