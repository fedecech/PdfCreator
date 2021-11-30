package token;

public class ParagraphText extends Token {
    private String text;

    public ParagraphText(String text){
        super(TokenType.PARAGRAPH);
        this.text = text;
    }

    @Override
    public String toString() {
        return "token.ParagraphText{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }
}