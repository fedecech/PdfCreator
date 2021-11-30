package token;

abstract public class Token {
     public enum TokenType{
        PARAGRAPH,
        COMMAND
    }

    private TokenType type;

    protected Token(TokenType type){
        this.type = type;
    }
}