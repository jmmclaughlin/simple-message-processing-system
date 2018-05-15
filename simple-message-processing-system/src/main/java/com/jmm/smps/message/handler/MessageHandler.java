package com.jmm.smps.message.handler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.jmm.smps.message.Message;

public class MessageHandler extends Observable {
	
	private Map<String, List<Message>> sales = new HashMap<String, List<Message>>();
	
	public void processMessage(Message message) {
		
		
		addSale(message);
		
		System.out.println("Sale added!");
		System.out.println("-----------");
		System.out.println(message.toString());
		
		if (getMessagesReceivedCount() % 10 == 0) {
			
			showReport();			
		}
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
		
	}
	
	private long getMessagesReceivedCount () {
		
		long messageCount = 0;
		
		for (String productType : sales.keySet()) {
			messageCount += sales.get(productType).size();
		}
		
		return messageCount;
	}
	
	
	private void showReport() {
		
		System.out.println("Show report after every 10 messages received");
		//sales.stream().
		
		
	}

}
