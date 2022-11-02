package calculator;

import java.util.HashMap;

public class RegisterFactory {
	
	HashMap<String, Register> registerNames = new HashMap<String, Register>();

	public Register createRegister(String name) {
		
		Register zeroreg = new ZeroRegister();
		zeroreg.giveName(name);
		registerNames.put(name, zeroreg);
		
		return null;
	}
	
}
