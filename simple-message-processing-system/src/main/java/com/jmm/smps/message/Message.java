package com.jmm.smps.message;

import com.jmm.smps.message.type.MessageType;

public interface Message {
	
	public MessageType getType();
	
	public String getProductType();
	public Long getProductPrice();
	
}
