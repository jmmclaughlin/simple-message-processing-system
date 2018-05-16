package com.jmm.smps.message.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType3;
import com.jmm.smps.message.type.MessageType;
import com.jmm.smps.report.ReportGenerator;

public class MessageHandler extends Observable {
	
	private Map<String, List<Message>> saleMessages = new HashMap<String, List<Message>>();
	private boolean isPaused = false;
	private String productTypeToAdjust = null;
	private String adjustmentOperator = null;
	private Long adjustmentAmount = null;

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	public String getProductTypeToAdjust() {
		return productTypeToAdjust;
	}

	public String getAdjustmentOperator() {
		return adjustmentOperator;
	}

	public Long getAdjustmentAmount() {
		return adjustmentAmount;
	}

	public void processMessage(Message message) {
		
		addObserver(message);
		addSaleMessage(message);
		
		if (message.getType() == MessageType.TYPE_3) {
			
			this.productTypeToAdjust = message.getProductType();
			this.adjustmentOperator = ((MessageType3)message).getAdjustmentOperator();
			this.adjustmentAmount = ((MessageType3)message).getAdjustmentAmount();
			
			setChanged();
			notifyObservers();
			
			this.productTypeToAdjust = null;
			this.adjustmentOperator = null;
			this.adjustmentAmount = null;
		}
	
		if (getMessagesReceivedCount() % 10 == 0) {

			System.out.println(ReportGenerator.generateSalesReport(saleMessages));			
		}
		
		if (getMessagesReceivedCount() == 3) {
			
			System.out.println("System paused, 50 messages received, new messages no longer being accepted!");
			setPaused(true);
			System.out.println(ReportGenerator.generateAdjustmentsReport(saleMessages));
		}
	}
	
	private void addSaleMessage(Message message) {
		
		String productType = message.getProductType();
		
		if (saleMessages.containsKey(productType)) {
			List<Message> salesForProduct = (List<Message>)saleMessages.get(productType);
			salesForProduct.add(message);
		}
		else {
			List<Message> newProductSalesList = new ArrayList<Message>();
			newProductSalesList.add(message);
			saleMessages.put(productType, newProductSalesList);
		}
		
		System.out.println("Sale added!");
		System.out.println("-----------");
		System.out.println(message.toString());
	}
	
	public long getMessagesReceivedCount () {
		
		long messageReceivedCount = saleMessages.keySet().stream().mapToLong(key -> saleMessages.get(key).stream().count()).sum();
		
		return messageReceivedCount; 
	}

}
