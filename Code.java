/*
 * Translate each field into its corresponding binary value
 */

public class Code {

	protected static String Acode;
	protected static String destCode;
	protected static String compCode;
	protected static String jumpCode;

	protected String getAcode() {
		return Acode;
	}

	protected String getDestCode() {
		return destCode;
	}

	protected String getCompCode() {
		return compCode;
	}

	protected String getJumpCode() {
		return jumpCode;
	}

	public Code() {

		Acode = "";
		destCode = "";
		compCode = "";
		jumpCode = "";

	}

	/*
	 * A instruction - translate decimal to binary code
	 */
	protected static String toA(String line) {

		if (line != null) {

			
			int code = Integer.parseInt(line);

			Acode = Integer.toBinaryString(0x10000 | code).substring(1);

		}

		return Acode;

	}

	/*
	 * C instruction - dest
	 */
	protected static String toDest(String line) {

		if (line != null) {

			switch (line) {

			case "M":
				destCode = "001";
				break;
			case "D":
				destCode = "010";
				break;
			case "MD":
				destCode = "011";
				break;
			case "A":
				destCode = "100";
				break;
			case "AM":
				destCode = "101";
				break;
			case "AD":
				destCode = "110";
				break;
			case "AMD":
				destCode = "111";
				break;
			default:
				destCode = "000";
				break;

			}
		} else {

			destCode = "000";
		}
		return destCode;

	}

	/*
	 * C instruction - comp
	 */

	protected static String toComp(String line) {

		if (line != null) {
			switch (line) {

			case "0":
				compCode = "0101010";
				break;
			case "1":
				compCode = "0111111";
				break;

			case "D":
				compCode = "0001100";
				break;
			case "A":
				compCode = "0110000";
				break;
			case "M":
				compCode = "1110000";
				break;

			case "-1":
				compCode = "0111010";

				break;
			case "!D":
				compCode = "0001111";
				break;
			case "!A":
				compCode = "0110011";
				break;
			case "!M":
				compCode = "1110001";
				break;
			case "-D":
				compCode = "0001111";
				break;
			case "-A":
				compCode = "0110011";
				break;
			case "-M":
				compCode = "1110011";
				break;
			case "D+1":
				compCode = "0011111";
				break;
			case "A+1":
				compCode = "0110111";
				break;
			case "M+1":
				compCode = "1110111";
				break;
			case "D-1":
				compCode = "0001110";
				break;
			case "A-1":
				compCode = "0110010";
				break;
			case "M-1":
				compCode = "1110010";
				break;
			case "D+A":
				compCode = "0000010";
				break;
			case "D+M":
				compCode = "1000010";
				break;
			case "D-A":
				compCode = "0010011";
				break;
			case "D-M":
				compCode = "1010011";
				break;
			case "A-D":
				compCode = "0000111";
				break;
			case "M-D":
				compCode = "1000111";
				break;
			case "D&A":
				compCode = "0000000";
				break;
			case "D&M":
				compCode = "1000000";
				break;
			case "D|A":
				compCode = "0010101";
				break;
			case "D|M":
				compCode = "1010101";
				break;
			default:
				compCode = "0000000";
				break;

			}

		} else {
			compCode = "0000000";
		}

		return compCode;
	}

	protected static String toJump(String line) {

		if (line != null) {
			switch (line) {

			case "JGT":
				jumpCode = "001";
				break;
			case "JEQ":
				jumpCode = "010";
				break;
			case "JGE":
				jumpCode = "011";
				break;
			case "JLT":
				jumpCode = "100";
				break;
			case "JNE":
				jumpCode = "101";
				break;
			case "JLE":
				jumpCode = "110";
				break;
			case "JMP":
				jumpCode = "111";
				break;
			default:
				jumpCode = "000";
				break;
			}
		} else {

			jumpCode = "000";
		}

		return jumpCode;
	}

}
