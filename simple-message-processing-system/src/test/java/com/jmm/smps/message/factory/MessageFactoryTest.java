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
	
	public static final String EXPECTED_PRODUCT_TYPE = "APPLE";
	public static final long EXPECTED_PRODUCT_PRICE = 10L;
	public static final long EXPECTED_OCCURRENCES = 5L;
	public static final String EXPECTED_ADJUSTMENT_OPERATOR = "+";
	public static final long EXPECTED_ADJUSTMENT_AMOUNT = 2L;

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
		assertEquals(EXPECTED_PRODUCT_TYPE, message.getProductType());
		assertEquals(EXPECTED_PRODUCT_PRICE, message.getProductPrice().longValue());
	}
	
	@Test
	public void testCreateMessageType2Object() {
		
		String testMessageInput = "apple:10:5";
		
		Message message = factory.create(testMessageInput, MessageType.TYPE_2);
		
		assertNotNull(message);
		assertTrue(message instanceof MessageType2);
		assertEquals(EXPECTED_PRODUCT_TYPE, message.getProductType());
		assertEquals(EXPECTED_PRODUCT_PRICE, message.getProductPrice().longValue());
		assertEquals(EXPECTED_OCCURRENCES, ((MessageType2)message).getOccurences().longValue());
	}

	@Test
	public void testCreateMessageType3Object() {
		
		String testMessageInput = "apple:10:+:2";
		
		Message message = factory.create(testMessageInput, MessageType.TYPE_3);
		
		assertNotNull(message);
		assertTrue(message instanceof MessageType3);
		assertEquals(EXPECTED_PRODUCT_TYPE, message.getProductType());
		assertEquals(EXPECTED_PRODUCT_PRICE, message.getProductPrice().longValue());
		assertEquals(EXPECTED_ADJUSTMENT_OPERATOR, ((MessageType3)message).getAdjustmentOperator());
		assertEquals(EXPECTED_ADJUSTMENT_AMOUNT, ((MessageType3)message).getAdjustmentAmount().longValue());
	}
}
