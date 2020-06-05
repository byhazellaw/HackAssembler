import java.util.HashMap;

/*
 * manage the symbol table - symbols only apply to A-instruction - start with @
 * 
 * Three kinds of symbols: 
 * 
 * variable symbols - memory locations where the programmer want to maintain values
 * label symbols - symbols inside () - destinations of goto instructions
 * pre-defined symbols - Screen, Keyboard, R0 etc.
 * 
 * Replace symbol with value - @decimal value - translate decimal value to binary
 * 
 */


public class SymbolTable {

	private static HashMap<String, String> symbolMap;
	
	
	public SymbolTable() {
		
		
		
		symbolMap = new HashMap<String, String>();
	
		//predefined symbols
		
		//R0-R15
		for (int i=0; i<16; i++) {
			
			String obj = Integer.toString(i);
			String key = "R"+obj;
			
			symbolMap.put(key, obj);
		}
		
		symbolMap.put("SCREEN", "16384");
		symbolMap.put("KBD", "24576");
		symbolMap.put("SP", "0");
		symbolMap.put("LCL", "1");
		symbolMap.put("ARG", "2");
		symbolMap.put("THIS", "3");
		symbolMap.put("THAT", "4");
		
		
	}
	
	
	protected HashMap<String, String> getMap() {
		
		return symbolMap;
		
	}
	
	protected void addSymbol(String symbol, String value) {
		
		symbolMap.put(symbol, value);
		
		return;
		
	}
	
	//return value of symbol
	protected String getSymbol(String symbol) {
		
		
		
		return symbolMap.get(symbol);
	}
	
	
}
