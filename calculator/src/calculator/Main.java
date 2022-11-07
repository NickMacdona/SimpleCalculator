package calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static HashMap<String, Integer> registers = new HashMap<String, Integer>();

	public static void main(String[] args) throws FileNotFoundException {
		Deque<String> queue = new LinkedList<String>();
		Deque<String> stack = new LinkedList<String>();
		if (args.length > 0) {
			File file = new File(args[0]);
			Scanner filescanner = new Scanner(file);
			while(filescanner.hasNextLine()) {
				String command = filescanner.nextLine();
				command.toLowerCase();
				if(command.contains("print")) {
					String[] printwords = command.split("\\s+");
					String currentqueue = "start"; 
					// take instruction from front of queue
					while (true) {

						currentqueue = queue.pollFirst();
						if (currentqueue == null) {
							break;
						}
						String[] instructionword = currentqueue.split("\\s+");
						

						// identify if registers are new 
						if (registers.containsKey(instructionword[0]) == false) {
							
							// create any new registers
							registers.put(instructionword[0], 0);
						}
					// adjust register with value presented
						adjustQueueRegister(instructionword[0], instructionword[1], instructionword[2]);
									
					
					// repeat while we empty queue
					}
					
					
					String currentstack = "start";
					// take instruction from top of stack
					while (true) {
						currentstack = stack.pollLast();
						if (currentstack == null) {
							break;
						}

						String[] instructionword = currentstack.split("\\s+");

						// identify if registers are new 
						if (registers.containsKey(instructionword[0]) == false) {
							// create any new registers
							registers.put(instructionword[0], 0);
						}
						if (registers.containsKey(instructionword[2]) == false) {
							// create any new registers
							registers.put(instructionword[2], 0);
						}
					// adjust register with value presented
						adjustStackRegister(instructionword[0], instructionword[1], instructionword[2]);
									
					// repeat while we empty stack
					}

					// get result
					int result = registers.getOrDefault(printwords[1], null);
					// print results to console
					System.out.println("Printed Result is: " + result);
					continue;
				}
					
				if (command.equals("quit")) {
					filescanner.close();
					System.exit(0);
				}
				;
				// determine if string is correct format
				String[] commandword = command.split("\\s+");
				if (commandword.length != 3) {
					System.out.println("Command not recognised (length)");
					continue;
				}
				if (checkIfCommand(commandword[1]) == false) {
					System.out.println("Command not recognised (unknown command)");
					continue;
				}
				if (checkIfRegisterValid(commandword[0]) == false) {
					System.out.println("Command not recognised (register invalid)");
					continue;
				}
				if (checkIfRegisterValid(commandword[2]) == false && checkIfNum(commandword[2]) == false) {
					System.out.println("Command not recognised (third word is not a register or number)");
					continue;
				}
				// determine what type of string we have
				// pass instruction to queue or stack depending on if it uses values or just registers
				if (checkIfNum(commandword[2])) {
					queue.add(command);
				} else {
					stack.add(command);
				} 
			}

		}
		else {
		Scanner scanner = new Scanner(System.in);
		String[] printwords = null;
		
		while (true) {
			System.out.println("Enter command");
			// read input into a string
			String command = scanner.nextLine();
			command.toLowerCase();
			if(command.contains("print")) {
				printwords = command.split("\\s+");
				String currentqueue = "start"; 
				// take instruction from front of queue
				while (true) {

					currentqueue = queue.pollFirst();
					if (currentqueue == null) {
						break;
					}
					String[] instructionword = currentqueue.split("\\s+");
					

					// identify if registers are new 
					if (registers.containsKey(instructionword[0]) == false) {
						
						// create any new registers
						registers.put(instructionword[0], 0);
					}
				// adjust register with value presented
					adjustQueueRegister(instructionword[0], instructionword[1], instructionword[2]);
								
				
				// repeat while we empty queue
				}
				
				
				String currentstack = "start";
				// take instruction from top of stack
				while (true) {
					currentstack = stack.pollLast();
					if (currentstack == null) {
						break;
					}

					String[] instructionword = currentstack.split("\\s+");

					// identify if registers are new 
					if (registers.containsKey(instructionword[0]) == false) {
						// create any new registers
						registers.put(instructionword[0], 0);
					}
					if (registers.containsKey(instructionword[2]) == false) {
						// create any new registers
						registers.put(instructionword[2], 0);
					}
				// adjust register with value presented
					adjustStackRegister(instructionword[0], instructionword[1], instructionword[2]);
								
				// repeat while we empty stack
				}

				// get result
				int result = registers.getOrDefault(printwords[1], null);
				// print results to console
				System.out.println("Printed Result is: " + result);
				continue;
			}
				
			if (command.equals("quit")) {
				scanner.close();
				System.exit(0);
			}
			;
			// determine if string is correct format
			String[] commandword = command.split("\\s+");
			if (commandword.length != 3) {
				System.out.println("Command not recognised (length)");
				continue;
			}
			if (checkIfCommand(commandword[1]) == false) {
				System.out.println("Command not recognised (unknown command)");
				continue;
			}
			if (checkIfRegisterValid(commandword[0]) == false) {
				System.out.println("Command not recognised (register invalid)");
				continue;
			}
			if (checkIfRegisterValid(commandword[2]) == false && checkIfNum(commandword[2]) == false) {
				System.out.println("Command not recognised (third word is not a register or number)");
				continue;
			}
			// determine what type of string we have
			// pass instruction to queue or stack depending on if it uses values or just registers
			if (checkIfNum(commandword[2])) {
				queue.add(command);
			} else {
				stack.add(command);
			} 
		}
		}
		
	}
	public static void adjustQueueRegister(String register, String operation, String number) {
		int regvalue = registers.getOrDefault(register, 0);
		int value = Integer.parseInt(number);
		switch(operation) {
			case "add":
				registers.put(register, (regvalue+value));
				break;
			case "subtract":
				registers.put(register, (regvalue-value));
				break;
			case "multiply":
				registers.put(register, (regvalue*value));
				break;	
		}
		
	}
	
	public static void adjustStackRegister(String register, String operation, String register2) {
		int regvalue = registers.getOrDefault(register, 0);
		int regvalue2 = registers.getOrDefault(register2, 0);
		switch(operation) {
			case "add":
				registers.put(register, (regvalue+regvalue2));
				break;
			case "subtract":
				registers.put(register, (regvalue-regvalue2));
				break;
			case "multiply":
				registers.put(register, (regvalue*regvalue2));
				break;
		}
		
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
		if (string.equals("add")) {
			return true;
		} else if(string.equals("subtract")) {
			return true;			
		} else if(string.equals("multiply")) {
			return true;			
		} else {
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