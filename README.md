# simple-message-processing-system

This is a Maven project and is run as a standalone Java application.

User instructions and output displayed in console(System.out)

1) First the messaging system prompts the user to input the type of message to send. Input either 1, 2, or 3. Then hit <return>.
2) Alternatively enter X and hit return to exit the messaging system.
3) Once the message type has been entered the user should enter a sample message for the chosen message type
    
e.g

for message type 1 an example message is apple:10 i.e <product_type>:<product_price>
	
for message type 2 an example message is apple:10:5 i.e <product_type>:<product_price>:<sale_occurrences>

for message type 3 and example message is apple:10:+:2 i.e <product_type>:<product_price>:<adjustment_operator>:<adjustment_amount>
    
4) Hit <return> to submit the message.
5) After 10 messages have been submitted and processed then a report should be displayed in the console(System.out) showing the total number of sales and total sales value for each product type. The user can then continue to submit further sale messages.
6) After 50 messages have been submitted and processed then a report should be displayed in the console(System.out) showing all the sale adjustments that have been made for each product type. The system will then pause and not allow any more sale messages to be submitted.
