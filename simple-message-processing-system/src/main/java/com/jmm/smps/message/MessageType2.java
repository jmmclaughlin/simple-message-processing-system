package com.jmm.smps.message;

import com.jmm.smps.message.type.MessageType;

public class MessageType2 extends Message {
	
	private Long occurences;

	public MessageType2() {};
	
	public MessageType2(String productType, Long productPrice, Long occurrences) {
		setProductType(productType);
		setProductPrice(productPrice);
		this.occurences = occurrences;
	}
	
	public Long getOccurences() {
		return occurences;
	}

	public void setOccurences(Long occurences) {
		this.occurences = occurences;
	}
	
	public Long getTotalSalesValue() {
		
		return getProductPrice()*occurences;
	}
	
	@Override
	public MessageType getType() {
		return MessageType.TYPE_2;
	}
	
	@Override
	public String toString() {
		
		return "ProductType=" + super.getProductType()
				+ "\n"
				+ "ProductPrice=" + super.getProductPrice() + "p"
				+ "\n"
				+ "Occurrences=" + occurences
				+ "\n\n";
	}

}
