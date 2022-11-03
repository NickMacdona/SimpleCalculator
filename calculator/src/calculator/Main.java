package calculator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	HashMap<String, Integer> registers = new HashMap<String, Integer>();

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Deque<String> queue = new LinkedList<String>();
		Deque<String> stack = new LinkedList<String>();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter command");
		
		while (true) {
			// read input into a string
			String command = scanner.nextLine();
			command.toLowerCase();
			if(command=="print") {
				scanner.close();
				break;
			}
				
			if (command == "quit") {
				scanner.close();
				System.exit(0);
			}
			;
			// determine if string is correct format
			String[] commandword = command.split("\\s+");
			if (commandword.length > 3) {
				System.out.println("Command not recognised");				
			}
			if (checkIfCommand(commandword[1]) == false) {
				System.out.println("Command not recognised");
			}
			if (checkIfRegisterValid(commandword[0]) == false) {
				System.out.println("Command not recognised");
			}
			
			// determine what type of string we have
			// pass instruction to queue or stack depending on if it uses values or just registers
			if (checkIfNum(commandword[2])) {
				queue.add(command);
			} else {
				stack.add(command);
			} 
		}

		// take instruction from front of queue
		
		// identify if registers are new 
		
		// create any new registers
		
		// adjust register with value presented
		
		// repeat while we empty queue
		
		// take instruction from top of stack
		
		// adjust register value
		
		// repeat until stack is empty
		
		// print result to console
	}
	
	public static boolean checkIfNum(String string) {
		char[] chars = string.toCharArray();
		for (char c : chars) {
			if(Character.isDigit(c)) {
				}
			else {
				return false;
			}
		}
		return true;
			
	}
	
	public static boolean checkIfCommand(String string) {
		if (string == "add") {
			return true;
		}
		else if(string == "subtract") {
			return true;			
		}
		else if(string == "multiply") {
			return true;			
		}
		else {
			return false;
		}
	}
	
	public static boolean checkIfRegisterValid(String string) {
		char[] chars = string.toCharArray();
		for (char c : chars) {
			if(Character.isLetterOrDigit(c)) {
				}
			else {
				return false;
			}
		}
		return true;		
	}
}