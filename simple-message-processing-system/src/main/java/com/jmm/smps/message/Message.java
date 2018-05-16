package com.jmm.smps.message;

import java.util.Observable;
import java.util.Observer;

import com.jmm.smps.message.handler.MessageHandler;
import com.jmm.smps.message.type.MessageType;

public abstract class Message implements Observer{
	
	private String productType;
	private Long productPrice;
	
	public abstract MessageType getType();
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Long getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public void update(Observable observableMessageHandler, Object arg) {
		
		MessageHandler messageHandler = (MessageHandler)observableMessageHandler;
		
		if (getProductType().equals(messageHandler.getProductTypeToAdjust())) {
			
			Long adjustmentAmount = messageHandler.getAdjustmentAmount();
			
			switch (messageHandler.getAdjustmentOperator()) {
			
				case "+":
					setProductPrice(getProductPrice() + adjustmentAmount);
					break;
				case "-":
					setProductPrice(getProductPrice() - adjustmentAmount);
					break;
				case "*":
					setProductPrice(getProductPrice() * adjustmentAmount);
					break;
				default:
					break;
			}
		}
	}

}
