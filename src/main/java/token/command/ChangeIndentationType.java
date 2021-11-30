package token.command;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

public class ChangeIndentationType extends Command {
    public enum IndentType{
        NOFILL,
        FILL,
    }

    private IndentType type = IndentType.NOFILL;

    public ChangeIndentationType(String lit){
        super(lit);
    }

    public ChangeIndentationType(String lit, IndentType indentType){
        super(lit);
        this.type = indentType;
    }

    @Override
    public void apply(Document document, Paragraph currentParagraph, Text currentText) {
        if (currentParagraph.getChildren().size() > 0) new NewParagraph(getLit()).apply(document, currentParagraph, currentText);
        switch (type){
            case NOFILL:
                currentParagraph.setTextAlignment(TextAlignment.LEFT);
                break;
            case FILL:
                currentParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
                break;
        }
    }

    @Override
    public String toString() {
        return "token.command.ChangeIndentationType{" +
                "type=" + type +
                '}';
    }
}
