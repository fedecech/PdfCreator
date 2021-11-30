package token.command;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import token.Token;

abstract public class Command extends Token {
    private String value;
    private String lit;
    public static final char Prefix = '.';

    public Command(String lit){
        super(Token.TokenType.COMMAND);
        this.value = lit.substring(1);
        this.lit = lit;
    }

    @Override
    public String toString() {
        return "token.command.Command{" +
                "value='" + value + '\'' +
                ", lit='" + lit + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public String getLit() {
        return lit;
    }

    abstract public void apply(Document document, Paragraph currentParagraph,  Text currentText);
}