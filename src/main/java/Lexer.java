import token.command.*;
import token.ParagraphText;
import token.Token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    class CompilationException extends Exception{
        CompilationException(int line, String msg){
            super(String.format("Error at line %d: %s", line, msg));
        }
    }

    private int current = 0;
    private int start = 0;
    private int line = 1;
    private String program;

    Lexer(String program){
        this.program = program;
    }

    public List<Token> parse(String program){
        reset(program);
        List<Token> tokens = new ArrayList<>();
        try {
            tokens = parseProgram();
        }catch (CompilationException e){
            System.err.print(e);
            System.exit(1);
        }
        return tokens;
    }

    public List<Token> parse() {
        List<Token> tokens = new ArrayList<>();
        try {
            tokens = parseProgram();
        }catch (CompilationException e){
            System.err.print(e);
            System.exit(1);
        }
        return tokens;
    }

    private List<Token> parseProgram() throws CompilationException {
        ArrayList<Token> tokens = new ArrayList<>();

        while (!isEOF()){
            this.start = this.current;
            switch (peek()){
                case Command.Prefix:
                    tokens.add(digestCommand());
                    break;
                case '\n': break;
                default:
                    tokens.add(digestParagraph());
                    break;
            }
            incrementLine();
            if (!isEOF()) next();
        }

        return tokens;
    }

    private Token digestParagraph(){
        while(peek() != '\n' && !isEOF()) next();

        String value = subProgram(start, current);
        return new ParagraphText(value);
    }

    private Token digestCommand() throws CompilationException {
        while(peek() != '\n' && !isEOF()) next();

        String lit = subProgram(start, current);

        if (lit.contains("indent")) return digestIndentCommand(lit);

        Command command = null;
        switch (lit){
            case ".large":
            case ".regular":
            case ".bold":
            case ".italics":
            case ".normal":
                command = new ApplyStyle(lit);
                break;
            case ".nofill":
                command = new ChangeIndentationType(lit);
                break;
            case ".fill":
                command = new ChangeIndentationType(lit, ChangeIndentationType.IndentType.FILL);
                break;
            case ".paragraph":
                command = new NewParagraph(lit);
                break;
            default:
                throw new CompilationException(line, String.format("Invalid command '%s'", lit));
        }
        return command;
    }

    private Token digestIndentCommand(String lit) throws CompilationException {
        try {
            return new Indent(lit, Integer.parseInt(lit.split(" ")[1]));
        }catch (Exception e){
            String msg = String.format("Wrong indent command format.\nFound: '%s'\nExpected: [.indent <number>]", lit);
            throw  new CompilationException(line, msg);
        }
    }


    private String subProgram(int start, int end){
        return this.program.substring(start, end);
    }

    private boolean isEOF(){
        return current >= program.length();
    }

    private void incrementLine(){
        this.line++;
    }

    private char peek(){
        if (isEOF()) return '\0';
        return this.program.charAt(this.current);
    }

    private char next(){
        return this.program.charAt(current++);
    }

    private void reset(String program){
        this.current = 0;
        this.line = 1;
        this.start = 0;
        this.program = program;
    }
}
