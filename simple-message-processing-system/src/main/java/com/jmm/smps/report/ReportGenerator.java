package com.jmm.smps.report;

import java.util.List;
import java.util.Map;

import com.jmm.smps.message.Message;

public interface ReportGenerator {
	
	public String generateSalesReport(Map<String, List<Message>> salesData);
	public String generateAdjustmentsReport(Map<String, List<Message>> salesData);

}
