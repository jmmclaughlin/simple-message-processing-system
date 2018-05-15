package com.jmm.smps.message;

import com.jmm.smps.message.type.MessageType;

public class MessageType2 extends MessageType1 {
	
	private Long occurences;

	public Long getOccurences() {
		return occurences;
	}

	public void setOccurences(Long occurences) {
		this.occurences = occurences;
	}
	
	@Override
	public MessageType getType() {
		return MessageType.TYPE_2;
	}

}
