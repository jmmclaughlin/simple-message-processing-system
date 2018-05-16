package com.jmm.smps.message.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.jmm.smps.message.MessageDataKeys;

public class MessageType2Parser implements MessageParser {
	
	@Override
	public Map<String, Object> parse(String srcMessage) {

		if (srcMessage != null && srcMessage.length() > 0) {

			StringTokenizer tokenizer = getTokenizer(srcMessage);
			String productType = tokenizer.nextToken();
			Long productPrice = Long.valueOf(tokenizer.nextToken());
			Long occurrences = Long.valueOf(tokenizer.nextToken());

			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put(MessageDataKeys.PRODUCT_TYPE.name(), productType.toUpperCase());
			dataMap.put(MessageDataKeys.PRODUCT_PRICE.name(), productPrice);
			dataMap.put(MessageDataKeys.OCCURRENCES.name(), occurrences);

			return dataMap;
		}

		return null;
	}
}
