package com.jmm.smps.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType1;
import com.jmm.smps.message.MessageType2;
import com.jmm.smps.message.MessageType3;

import junit.framework.TestCase;

public class SystemOutReportGeneratorTest extends TestCase {

	ReportGenerator reportGenerator;
	Map<String, List<Message>> salesData;
	
	@Before
	protected void setUp() throws Exception {
		
		reportGenerator = new SystemOutReportGenerator();
		
		salesData = new HashMap<>();
		
		List<Message> messages = new ArrayList<>();
		Message message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(20L);
		messages.add(message);
		
		message = new MessageType2();
		message.setProductType("APPLE");
		message.setProductPrice(5L);
		((MessageType2)message).setOccurences(10L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("APPLE");
		message.setProductPrice(10L);
		messages.add(message);
		
		salesData.put("APPLE", messages);
		
		messages = new ArrayList<>();
		message = new MessageType1();
		message.setProductType("ORANGE");
		message.setProductPrice(10L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("ORANGE");
		message.setProductPrice(5L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("ORANGE");
		message.setProductPrice(15L);
		messages.add(message);
		
		salesData.put("ORANGE", messages);
		
		messages = new ArrayList<>();
		message = new MessageType1();
		message.setProductType("BANANA");
		message.setProductPrice(12L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("BANANA");
		message.setProductPrice(8L);
		messages.add(message);
		
		message = new MessageType1();
		message.setProductType("BANANA");
		message.setProductPrice(25L);
		messages.add(message);
		
		salesData.put("BANANA", messages);
				
	}

	@Test
	public void testGenerateSalesReport() {
		
		String expectedReport = "\nShowing  sales report after every 10 messages received\n\n\n" + 
				"PRODUCT_TYPE        | TOTAL_SALES         | TOTAL_VALUE        \n" + 
				"---------------------------------------------------------------\n" +
				"APPLE               | 13                  | £0.90               \n" + 
				"BANANA              | 3                   | £0.45               \n" + 
				"ORANGE              | 3                   | £0.30               \n";
		
		String generatedReport = reportGenerator.generateSalesReport(salesData);
		assertEquals(expectedReport, generatedReport );
	}
	
	@Test
	public void testGenerateAdjustmentsReport() {
		
		// Add some adjustment messages
		Message message = new MessageType3();
		message.setProductType("BANANA");
		message.setProductPrice(12L);
		((MessageType3)message).setAdjustmentOperator("+");
		((MessageType3)message).setAdjustmentAmount(5L);
		salesData.get("BANANA").add(message);
		
		message = new MessageType3();
		message.setProductType("BANANA");
		message.setProductPrice(10L);
		((MessageType3)message).setAdjustmentOperator("-");
		((MessageType3)message).setAdjustmentAmount(10L);
		salesData.get("BANANA").add(message);
		
		message = new MessageType3();
		message.setProductType("APPLE");
		message.setProductPrice(8L);
		((MessageType3)message).setAdjustmentOperator("*");
		((MessageType3)message).setAdjustmentAmount(2L);
		salesData.get("APPLE").add(message);
		
		
		String expectedReport = "\n\nShow adjustment report\n\n" + 
				"PRODUCT_TYPE        | ADJUSTMENTS         \n" + 
				"-----------------------------------------APPLE               | *2.0p\n" + 
				"BANANA              | +5.0p\n" + 
				"BANANA              | -10.0p\n";
		
		String generatedReport = reportGenerator.generateAdjustmentsReport(salesData);
		assertEquals(expectedReport, generatedReport);
	}

}
