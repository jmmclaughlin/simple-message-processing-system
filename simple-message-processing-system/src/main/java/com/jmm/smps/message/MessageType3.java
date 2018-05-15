package com.jmm.smps.message;

import com.jmm.smps.message.type.MessageType;

public class MessageType3 extends MessageType1 {
	
	private String adjustmentOperator;
	private Long adjustmentAmount;
	
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
	
}
