package calculator;

import java.util.ArrayList;

public class RegisterFactory {
	
	ArrayList<Register> registers = new ArrayList<Register>();

	public Register createRegister(String name) {
		
		Register zeroreg = new ZeroRegister();
		zeroreg.giveName(name);
		registers.add(zeroreg);
		
		return null;
	}
	
}
