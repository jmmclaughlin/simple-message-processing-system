package com.jmm.smps.message;

import com.jmm.smps.message.type.MessageType;

public class MessageType3 extends Message {
	
	private String adjustmentOperator;
	private Long adjustmentAmount;
	
	public MessageType3() {};
	
	public MessageType3(String productType, Long productPrice, String adjustmentOperator, Long adjustmentAmount) {
		setProductType(productType);
		setProductPrice(productPrice);
		this.adjustmentOperator = adjustmentOperator;
		this.adjustmentAmount = adjustmentAmount;
	}
	
	public String getAdjustmentOperator() {
		return adjustmentOperator;
	}
	public void setAdjustmentOperator(String adjustmentOperator) {
		this.adjustmentOperator = adjustmentOperator;
	}
	public Long getAdjustmentAmount() {
		return adjustmentAmount;
	}
	public void setAdjustmentAmount(Long adjustmentAmount) {
		this.adjustmentAmount = adjustmentAmount;
	}
	
	@Override
	public MessageType getType() {
		return MessageType.TYPE_3;
	}
	
	@Override
	public String toString() {
		
		return "ProductType=" + super.getProductType()
				+ "\n"
				+ "ProductPrice=" + super.getProductPrice() + "p"
				+ "\n"
				+ "Adjustment=" + adjustmentOperator + adjustmentAmount + "p"
				+ "\n\n";
	}

}
