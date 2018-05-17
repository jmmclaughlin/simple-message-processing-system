package com.jmm.smps;

import java.util.Scanner;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.factory.MessageFactory;
import com.jmm.smps.message.handler.MessageHandler;
import com.jmm.smps.message.type.MessageType;
import com.jmm.smps.report.SystemOutReportGenerator;


public class App 
{
	public static final String INPUT_MESSAGE_TYPE_PROMPT = "Choose new message type [options are 1, 2 or 3] or enter X to exit:\n";
	
    public static void main( String[] args )
    {
        boolean closeApp = false;
        MessageHandler messageHandler = new MessageHandler(new SystemOutReportGenerator());
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Simple Message Processing System");
    	System.out.println("--------------------------------\n");
    	
		while (!closeApp) {
			
			if (!messageHandler.isPaused()) {

				System.out.println(INPUT_MESSAGE_TYPE_PROMPT);
				
				String input = scanner.nextLine();
				String saleMessage = null;
				Message message = null;

				try {
					switch (input) {
					case "1":
						System.out.println("Input message of Type 1 in the format 'product_type:price(p)' e.g Apple:10 :");
						saleMessage = scanner.nextLine();
						message = MessageFactory.create(saleMessage, MessageType.TYPE_1);
						messageHandler.processMessage(message);
						break;
					case "2":
						System.out.println("Input message of Type 2 in the format 'product_type:price(p):occurrences' e.g Apple:10:20 :");
						saleMessage = scanner.nextLine();
						message = MessageFactory.create(saleMessage, MessageType.TYPE_2);
						messageHandler.processMessage(message);
						break;
					case "3":
						System.out.println("Input message of Type 3 in the format 'product_type:price(p):<operator>adjustment(p)' e.g 'Apple:10:+:5' :");
						saleMessage = scanner.nextLine();
						message = MessageFactory.create(saleMessage, MessageType.TYPE_3);
						messageHandler.processMessage(message);
						break;
					case "X":
						closeApp = true;
						break;
					default:
						throw new RuntimeException();
					}
				}
				catch (Exception e) {
					System.out.println("\nInvalid input!!\n\n");
				}
			}
			else {
				scanner.close();
			}
		}
		
		System.out.println("\nExiting Simple Message Processing System\n");
		
		System.exit(0);
    }
}
