package com.jmm.smps.message.handler;

import org.junit.Test;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType1;

import junit.framework.TestCase;

public class MessageHandlerTest extends TestCase {
	
	MessageHandler handler;

	@Override
	protected void setUp() {
		handler = new MessageHandler();
	}
	
	@Test
	public void testReceivedCountAfterFirstMessage() {
		
		Message message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		
		handler.processMessage(message);
		
		assertEquals(1, handler.getMessagesReceivedCount());
	}
	
	@Test
	public void testReceivedCountAfterTwoMessages() {
		
		Message message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		
		handler.processMessage(message);
		
		message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		
		handler.processMessage(message);
		
		assertEquals(2, handler.getMessagesReceivedCount());
	}
	
	
}
