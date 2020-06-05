import java.util.HashMap;

/*
 * Unpacks each instruction into its underlying fields
 */

public class Parser {

	private String line;
	private static String dest;
	private static String comp;
	private static String jump;
	
	

	/*
	 * Public constructor If string[0] == @ - a instruction - translate to binary
	 * code If string[0] != @ - c instruction - divide instruction into dest, comp
	 * and jump fields
	 */
	public Parser(String line) {

		dest = "";
		comp = "";
		jump = "";
		

		this.line = line;

		unpackC(line);
		

	}

	/*
	 * C instruction consists of dest = comp ; jump
	 */
	protected static void unpackC(String line) {

		int eq = line.indexOf('=');
		int co = line.indexOf(';');

		if (eq != -1 && co == -1) { // jump is omitted

			dest = line.substring(0, eq);
			comp = line.substring(eq + 1);
			jump = null;

		} else if (eq == -1 && co != -1) { // dest is omitted

			dest = null;
			comp = line.substring(0, co);
			jump = line.substring(co + 1);

		}
		
		return;

	}

	protected String getLine() {
		return line;
	}

	protected String getDest() {
		return dest;
	}

	protected String getComp() {
		return comp;
	}

	protected String getJump() {
		return jump;
	}

	
}
