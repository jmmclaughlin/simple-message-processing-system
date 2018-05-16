package com.jmm.smps.message.factory;

import org.junit.Test;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType1;
import com.jmm.smps.message.MessageType2;
import com.jmm.smps.message.MessageType3;
import com.jmm.smps.message.type.MessageType;

import junit.framework.TestCase;

public class MessageFactoryTest extends TestCase {
	
	MessageFactory factory;

	@Override
	protected void setUp() {
		final MessageFactory factory = new MessageFactory();
	}
	
	@Test
	public void testCreateMessageType1Object() {
		
		String testMessageInput = "apple:10";
		Message message = factory.create(testMessageInput, MessageType.TYPE_1);
		
		assertNotNull(message);
		assertTrue(message instanceof MessageType1);
	}
	
	@Test
	public void testCreateMessageType2Object() {
		
		String testMessageInput = "apple:10:5";
		
		Message message = factory.create(testMessageInput, MessageType.TYPE_2);
		
		assertNotNull(message);
		assertTrue(message instanceof MessageType2);
	}

	@Test
	public void testCreateMessageType3Object() {
		
		String testMessageInput = "apple:10:+:2";
		
		Message message = factory.create(testMessageInput, MessageType.TYPE_3);
		
		assertNotNull(message);
		assertTrue(message instanceof MessageType3);
	}
}
