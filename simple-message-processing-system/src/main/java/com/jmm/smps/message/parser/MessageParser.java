package com.jmm.smps.message.parser;

import java.util.Map;

import com.jmm.smps.message.Message;

public interface MessageParser {
	
	public Map<String, Object> parse(String srcMessage);

}
