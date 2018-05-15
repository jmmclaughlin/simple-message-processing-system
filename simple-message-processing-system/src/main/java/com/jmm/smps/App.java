package com.jmm.smps;

import java.util.Scanner;

import com.jmm.smps.message.Message;
import com.jmm.smps.message.factory.MessageFactory;
import com.jmm.smps.message.handler.MessageHandler;
import com.jmm.smps.message.type.MessageType;

/**
 * Hello world!
 *
 */
public class App 
{
	public static final String INPUT_MESSAGE_TYPE_PROMPT = "Choose new message type [options are 1, 2 or 3]:\n";
	
    public static void main( String[] args )
    {
        boolean closeApp = false;
        MessageHandler messageHandler = new MessageHandler();
    	Scanner scanner = new Scanner(System.in);
    	
    	System.out.println("Simple Message Processing System");
    	System.out.println("--------------------------------\n");
    	
		System.out.println(INPUT_MESSAGE_TYPE_PROMPT);

		while (!closeApp) {
			
			String input = scanner.nextLine();
			System.out.println("'" + input + "'");

			switch (input) {
			case "1":
				
				System.out.println("Input message of Type 1 in the format 'product_type:price(p)' e.g Apple:10 :");
				String saleMessage = scanner.nextLine();
				System.out.println("'" + saleMessage + "'");
				Message message = MessageFactory.create(saleMessage, MessageType.TYPE_1);
				messageHandler.processMessage(message);
				break;
			case "2":
				System.out.println("Input message of Type 2 in the format 'product_type:price(p):occurences' e.g Apple:10:20 :");
				
				break;
			case "3":
				System.out.println("Input message of Type 3 in the format 'product_type:price(p):<operator>adjustment(p)' e.g 'Apple:10:+:5' :");
				
				break;
			case "X":
				closeApp = true;
				break;
			default:
				System.out.println("Invalid input!!\n");
				break;
			}
			
			
			
			System.out.println(INPUT_MESSAGE_TYPE_PROMPT);
		}
		
		System.out.println("\nExiting Simple Message Processing System\n");
		
		System.exit(0);
    }
}
