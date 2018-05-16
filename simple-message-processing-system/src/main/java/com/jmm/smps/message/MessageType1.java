package com.jmm.smps.message;

import java.util.Observer;

import com.jmm.smps.message.type.MessageType;

public class MessageType1 extends Message implements Observer{
	
	public MessageType1() {};
	
	public MessageType1(String productType, Long productPrice) {
		super.setProductType(productType);
		super.setProductPrice(productPrice);
	}
	
	@Override
	public MessageType getType() {
		return MessageType.TYPE_1;
	}

	@Override
	public String toString() {
		
		return "ProductType=" + getProductType()
				+ "\n"
				+ "ProductPrice=" + getProductPrice() + "p"
				+ "\n\n";
	}
}
