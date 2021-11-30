import token.Token;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PdfCreator {
    public static final String TXT_EXT = ".txt";
    public static final String PDF_EXT = ".pdf";
    public static final String INPUT_DEFAULT = "input";
    public static final String OUTPUT_DEFAULT = "output";

    public static void usage (int exitCode){
        System.out.println("USAGE:");
        System.out.println("java <input_path> <output_path>");
        System.out.println("EXAMPLE: java my_pdf_text.txt my_pdf.pdf");
        System.exit(exitCode);
    }

    public static void main(String[] args) throws Exception {
        String inputFile;
        String outputFile;
        if (args.length < 3){
            inputFile = INPUT_DEFAULT + TXT_EXT;
            outputFile = OUTPUT_DEFAULT + PDF_EXT;
        }else{
            inputFile = args[0];
            outputFile = args[1];
            if (!inputFile.split(".")[1].equals(TXT_EXT) || !outputFile.split(".")[1].equals(PDF_EXT)) usage(1);
        }

        System.out.println(String.format("Retrieving file '%s'...", inputFile));
        String program = new String(Files.readAllBytes(Paths.get(inputFile)));

        Lexer lexer =  new Lexer(program);
        System.out.println("Lexing program...");
        List<Token> tokens = lexer.parse();

        System.out.println("Parsing program...");
        Parser parser = new Parser(outputFile);
        parser.parse(tokens);

        System.exit(0);
    }
}
