package calculator;

public class ZeroRegister implements Register {
	int value = 0;
	String registerName = "";

	public void giveName(String name) {
		registerName = name;		
	}
	

	public String getName() {
		return registerName;
	}
}
