package com.jmm.smps.message.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageDataKeys;
import com.jmm.smps.message.MessageType1;
import com.jmm.smps.message.MessageType2;
import com.jmm.smps.message.MessageType3;
import com.jmm.smps.message.parser.MessageParser;
import com.jmm.smps.message.parser.MessageType1Parser;
import com.jmm.smps.message.parser.MessageType2Parser;
import com.jmm.smps.message.parser.MessageType3Parser;
import com.jmm.smps.message.type.MessageType;

public class MessageFactory {
	
	private static final Map<MessageType, MessageParser> parsers = Collections.unmodifiableMap(new HashMap<MessageType, MessageParser>() {
		{
			put(MessageType.TYPE_1, new MessageType1Parser());
			put(MessageType.TYPE_2, new MessageType2Parser());
			put(MessageType.TYPE_3, new MessageType3Parser());
		}
	});
	
	public static Message create(String srcMessage, MessageType messageType) {
		
		Map<String, Object> data = null;
		Message message = null;
		MessageParser parser = parsers.get(messageType);
		
		switch (messageType) {
		
		case TYPE_1 :
			data = parser.parse(srcMessage);
			message = new MessageType1((String)data.get(MessageDataKeys.PRODUCT_TYPE.name()), (Long)data.get(MessageDataKeys.PRODUCT_PRICE.name()));
			break;
		case TYPE_2 :
			data = parser.parse(srcMessage);
			message = new MessageType2((String)data.get(MessageDataKeys.PRODUCT_TYPE.name()), (Long)data.get(MessageDataKeys.PRODUCT_PRICE.name()), (Long)data.get(MessageDataKeys.OCCURRENCES.name()));
			break;
		case TYPE_3 :
			data = parser.parse(srcMessage);
			message = new MessageType3((String)data.get(MessageDataKeys.PRODUCT_TYPE.name()), (Long)data.get(MessageDataKeys.PRODUCT_PRICE.name()), (String)data.get(MessageDataKeys.ADJUSTMENT_OPERATOR.name()), (Long)data.get(MessageDataKeys.ADJUSTMENT_AMOUNT.name()));
			break;
		default :
			break;
		}
		
		return message;
	}

}
