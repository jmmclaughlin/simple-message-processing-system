package com.jmm.smps.message.parser;

import java.util.Map;
import java.util.StringTokenizer;

public interface MessageParser {
	
	public Map<String, Object> parse(String srcMessage);
	
	public default StringTokenizer getTokenizer(String srcMessage) {
		
		return new StringTokenizer(srcMessage, ":");
	}
}
