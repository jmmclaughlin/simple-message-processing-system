package com.jmm.smps.parser;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jmm.smps.message.MessageDataKeys;
import com.jmm.smps.message.parser.MessageType3Parser;

import junit.framework.TestCase;

public class MessageType3ParserTest extends TestCase {
	
	public MessageType3Parser parser;

	@Before
	protected void setUp() throws Exception {
		parser = new MessageType3Parser();
	}

	@Test
	public void test() {
		
		Map<String, Object> dataMap = parser.parse("apple:10:+:5");
		
		assertEquals(4, dataMap.keySet().size());
		assertTrue(dataMap.containsKey(MessageDataKeys.PRODUCT_TYPE.name()));
		assertTrue(dataMap.containsKey(MessageDataKeys.PRODUCT_PRICE.name()));
		assertTrue(dataMap.containsKey(MessageDataKeys.ADJUSTMENT_OPERATOR.name()));
		assertTrue(dataMap.containsKey(MessageDataKeys.ADJUSTMENT_AMOUNT.name()));
	}

}
