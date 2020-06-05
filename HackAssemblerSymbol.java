
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class HackAssemblerSymbol {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		int lineCount = 0;
		SymbolTable symbol = new SymbolTable();

		
		  if (args.length > 0) {
		  
		  String inFile = args[0]; 
		  int dot = inFile.indexOf('.'); 
		  String outFileName = inFile.substring(0, dot) + ".hack";
		 
			Scanner scanner = new Scanner(new File(inFile));
			PrintWriter writer = new PrintWriter(outFileName, "UTF-8");

			// first pass
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				// ignore empty and documentation
				if (line.indexOf("/") == 0 || line.isEmpty()) {
					continue;
				}

				String instruction = cleanLines(line);

				if (instruction.charAt(0) == '(') {

					String key = fixLabel(instruction);

					
					
					symbol.addSymbol(key, Integer.toString(lineCount));


					
				} else {

					lineCount++;
				}

			}
			scanner.close();

			int n = 16;

			scanner = new Scanner(new File(inFile));
			// second pass
			while (scanner.hasNextLine()) {

				String line = scanner.nextLine();

				// ignore empty and documentation
				if (line.indexOf("/") == 0 || line.isEmpty()) {
					continue;
				}

				String instruction = cleanLines(line);
				String translatedCode = "";

				// A instruction
				if (instruction.charAt(0) == '@') {

					if (symbol.getMap().containsKey(instruction.substring(1))) {

						String value = symbol.getSymbol(instruction.substring(1));

						translatedCode = Code.toA(value);

						
						writer.println(translatedCode);
						
						
					} else if (instruction.substring(1).matches("[0-9]+")) {
						
						translatedCode = Code.toA(instruction.substring(1));
						
						writer.println(translatedCode);
						
						
					} else {

						symbol.addSymbol(instruction.substring(1), Integer.toString(n));

						translatedCode = Code.toA(Integer.toString(n));

						
						n++;
						
						writer.println(translatedCode);
					}

					
				} else if (instruction.charAt(0)=='('){
					
					continue;
					
					
					// C instruction
				} else {

					Parser p = new Parser(instruction);

					
					String d = p.getDest();
					String c = p.getComp();
					String j = p.getJump();

					
					Code decode = new Code();

					String dd = decode.toDest(d);
					String cc = decode.toComp(c);
					String jj = decode.toJump(j);

					
					
					translatedCode = "111" + cc + dd + jj;
					

					writer.println(translatedCode);
				}

				
			}

			scanner.close();
			writer.close();
		  }
	}

	// String processor
	protected static String cleanLines(String line) {

		String justCode;

		if (line.contains("/")) { // ignore comments

			justCode = line.split("/")[0];

		} else {

			justCode = line;
		}

		return justCode.replaceAll(" ", "");
	}

	protected static String fixLabel(String line) {

		return line.replaceAll("[()]", "");

	}

}
