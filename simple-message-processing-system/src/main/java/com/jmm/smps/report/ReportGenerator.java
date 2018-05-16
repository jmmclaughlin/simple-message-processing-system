package com.jmm.smps.report;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.MessageType2;
import com.jmm.smps.message.MessageType3;
import com.jmm.smps.message.type.MessageType;

public class ReportGenerator {

	public static String generateSalesReport(Map<String, List<Message>> salesData) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\nShowing  sales report after every 10 messages received\n\n");
		
		stringBuilder.append("\nPRODUCT_TYPE        | TOTAL_SALES         | TOTAL_VALUE        ");
		stringBuilder.append("\n---------------------------------------------------------------\n");
		
		for (String productType : salesData.keySet()) {
			
			List<Message> saleMessagesForProduct = salesData.get(productType);
			long salesCount = getSalesCountForProductType(saleMessagesForProduct);
			long salesTotal = getSalesTotalForProductType(saleMessagesForProduct);
			
			stringBuilder.append(String.format("%0$-19.19s", productType));
			stringBuilder.append(" | ");
			stringBuilder.append(String.format("%0$-19.19s",salesCount));
			stringBuilder.append(" | ");
			stringBuilder.append("£");
			stringBuilder.append(String.format("%0$-19.19s",String.format("%.2f", (new Double(salesTotal).doubleValue())/100)));
			stringBuilder.append("\n");
		}
		
		return stringBuilder.toString();
		
	}
	
	public static String generateAdjustmentsReport(Map<String, List<Message>> salesData) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
//		System.out.println("\nShow adjustment report\n");
//		
//		System.out.println("PRODUCT_TYPE        | ADJUSTMENTS         ");
//		System.out.println("-----------------------------------------");
//		
//		for (String productType : salesData.keySet()) {
//			
//			List<Message> saleMessagesType3ForProduct = salesData.get(productType).stream().filter(message -> message.getType() == MessageType.TYPE_3).collect(Collectors.toList());
//			
//			for (Message message : saleMessagesType3ForProduct) {
//
////				StringBuilder stringBuilder = new StringBuilder();
//				stringBuilder.append(String.format("%0$-19.19s", productType));
//				stringBuilder.append(" | ");
//				stringBuilder.append(((MessageType3)message).getAdjustmentOperator());
//				stringBuilder.append(new Double(((MessageType3)message).getAdjustmentAmount()));
//				stringBuilder.append("p");
//				stringBuilder.append("\n");
//				
//				System.out.println(stringBuilder.toString());
//			}
//
//		}
		
		stringBuilder.append("\n\nShow adjustment report\n");
		
		stringBuilder.append("\nPRODUCT_TYPE        | ADJUSTMENTS         ");
		stringBuilder.append("\n-----------------------------------------");
		
		for (String productType : salesData.keySet()) {
			
			List<Message> saleMessagesType3ForProduct = salesData.get(productType).stream().filter(message -> message.getType() == MessageType.TYPE_3).collect(Collectors.toList());
			
			for (Message message : saleMessagesType3ForProduct) {

				stringBuilder.append(String.format("%0$-19.19s", productType));
				stringBuilder.append(" | ");
				stringBuilder.append(((MessageType3)message).getAdjustmentOperator());
				stringBuilder.append(new Double(((MessageType3)message).getAdjustmentAmount()));
				stringBuilder.append("p");
				stringBuilder.append("\n");
			}
		}
		
		return stringBuilder.toString();
	}
	
	private static long getSalesCountForProductType(List<Message> saleMessagesForProduct) {
		
		long salesCount = 0; 
				
		for (Message message : saleMessagesForProduct) {
			
			MessageType messageType = message.getType();
			
			switch (messageType.name()) {
			case "TYPE_1":
			case "TYPE_3":
				salesCount += 1;
				break;
			case "TYPE_2":
				salesCount += ((MessageType2)message).getOccurences();
				break;
			default:
				break;
			}
		}
		
		return salesCount;
	}
	
	private static long getSalesTotalForProductType(List<Message> saleMessagesForProduct) {
		
		long salesTotal = 0; 
				
		for (Message message : saleMessagesForProduct) {
			
			MessageType messageType = message.getType();
			
			switch (messageType.name()) {
			case "TYPE_1":
			case "TYPE_3":
				salesTotal += message.getProductPrice().longValue();
				break;
			case "TYPE_2":
				salesTotal += ((MessageType2)message).getTotalSalesValue();
				break;
			default:
				break;
			}
		}
		
		return salesTotal;
	}
	
}
