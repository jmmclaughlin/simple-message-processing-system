package com.jmm.smps.message;

import java.util.Observable;
import java.util.Observer;

import com.jmm.smps.message.type.MessageType;

public class MessageType1 implements Message, Observer{
	
	private String productType;
	private Long productPrice;
	
	public MessageType1() {};
	
	public MessageType1(String productType, Long productPrice) {
		this.productType = productType;
		this.productPrice = productPrice;
	}
	
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
	public MessageType getType() {
		return MessageType.TYPE_1;
	}

	@Override
	public String toString() {
		
		return "ProductType=" + productType
				+ "\n"
				+ "ProductPrice=" + productPrice + "p"
				+ "\n\n";
	}

	@Override
	public void update(Observable messageHandler, Object arg) {
		
		
		
	}

}
