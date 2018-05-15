package com.jmm.smps.message.handler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.print.event.PrintServiceAttributeListener;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.type.MessageType;

public class MessageHandler extends Observable {
	
	private Map<String, List<Message>> sales = new HashMap<String, List<Message>>();
	
	public void processMessage(Message message) {
		
		addSale(message);
		
		//TODO need to record adjustments
		//TODO need to notify sale messages of an adjustment and update price
	
		if (getMessagesReceivedCount() % 10 == 0) {

			showReport();			
		}
		
		
		//TODO After 50 messages :
		// 1) pause app
		// 2) stop accepting messages
		// 3) show report of adjustments made to each sale type
		
	}
	
	private void addSale(Message message) {
		
		
		String productType = message.getProductType();
		
		if (sales.containsKey(productType)) {
			List<Message> salesForProduct = (List<Message>)sales.get(productType);
			salesForProduct.add(message);
		}
		else {
			List<Message> newProductSalesList = new ArrayList<Message>();
			newProductSalesList.add(message);
			sales.put(productType, newProductSalesList);
		}
		
		System.out.println("Sale added!");
		System.out.println("-----------");
		System.out.println(message.toString());
	}
	
	private long getMessagesReceivedCount () {
		
		long messageCount = 0;
		
		for (String productType : sales.keySet()) {
			messageCount += sales.get(productType).size();
		}
		
		return messageCount;
	}
	
	
	private void showReport() {
		
		System.out.println("Show report after every 10 messages received\n\n");
		
		System.out.println("PRODUCT_TYPE        | TOTAL_SALES         | TOTAL_VALUE        ");
		System.out.println("---------------------------------------------------------------");
		
		for (String productType : sales.keySet()) {
			
			List<Message> saleMessagesForProduct = sales.get(productType);
			long salesCount = saleMessagesForProduct.size();
			long salesTotal = saleMessagesForProduct.stream().mapToLong(saleMsg -> saleMsg.getProductPrice().longValue()).sum();
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(String.format("%0$-19.19s", productType));
			stringBuilder.append(" | ");
			stringBuilder.append(String.format("%0$-19.19s",salesCount));
			stringBuilder.append(" | ");
			stringBuilder.append("£");
			stringBuilder.append(String.format("%0$-19.19s",String.format("%.2f", (new Double(salesTotal).doubleValue())/100)));
			stringBuilder.append("\n");
			
			System.out.println(stringBuilder.toString());
		}
	}

}
