package com.jmm.smps.message.handler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType1;
import com.jmm.smps.message.MessageType3;
import com.jmm.smps.report.ReportGenerator;

import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class MessageHandlerTest extends TestCase {
	
	@Mock
	ReportGenerator mockReportGenerator;

	@InjectMocks
	@Spy
	private MessageHandler handler;
	
	@Override
	protected void setUp() {
		
		when(mockReportGenerator.generateSalesReport(any(Map.class))).thenReturn("TEST REPORT CONTENT");
		when(mockReportGenerator.generateAdjustmentsReport(any(Map.class))).thenReturn("TEST REPORT CONTENT");
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
	
	@Test
	public void testSalesReportCalledAfterTenMessages() {
	
		Message message = null;
		
		for ( int i=0; i < 9; i++) {
			message = new MessageType1();
			message.setProductType("APPLE");
			message.setProductPrice(10L);
			
			handler.processMessage(message);
		}
		
		verify(mockReportGenerator, never()).generateSalesReport(any(Map.class));
		
		message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		
		handler.processMessage(message);
		
		verify(mockReportGenerator).generateSalesReport(any(Map.class));
	}
	
	@Test
	public void testAdjustmentReportCalledAfterFiftyMessages() {
		
		Message message = null;
		
		for ( int i=0; i < 49; i++) {
			message = new MessageType1();
			message.setProductType("APPLE");
			message.setProductPrice(10L);
			
			handler.processMessage(message);
		}
		
		verify(mockReportGenerator, never()).generateAdjustmentsReport(any(Map.class));
		
		message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		
		handler.processMessage(message);
		
		verify(mockReportGenerator).generateAdjustmentsReport(any(Map.class));
	}

	@Test
	public void testIsPausedSetToTrueAfterFiftyMessages() {
	
		
		assertFalse(handler.isPaused());
		
		Message message = null;
		
		for ( int i=0; i < 50; i++) {
			message = new MessageType1();
			message.setProductType("APPLE");
			message.setProductPrice(10L);
			
			handler.processMessage(message);
		}
		
		assertTrue(handler.isPaused());
	}
	
	@Test
	public void testNotifyObserversMethodCalledWhenMessageType3Received() {
		
		Message message = new MessageType3();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		((MessageType3)message).setAdjustmentOperator("+");
		((MessageType3)message).setAdjustmentAmount(5L);
		
		handler.processMessage(message);
		
		verify(handler).notifyObservers();
	}
	
}
