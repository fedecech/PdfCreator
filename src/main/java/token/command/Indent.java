package token.command;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.UnitValue;

public class Indent extends Command {
    private int tabs;
    private static final int MULTIPLIER = 20;

    public Indent(String lit, int amount) {
        super(lit);
        this.tabs = amount;
    }

    @Override
    public void apply(Document document, Paragraph currentParagraph,  Text currentText) {
        UnitValue lastMarginLeft = currentParagraph.getMarginLeft();
        float lastMarginLeftValue = lastMarginLeft == null ? 0 : lastMarginLeft.getValue();

        if (currentParagraph.getChildren().size() > 0) new NewParagraph(getLit()).apply(document, currentParagraph, currentText);

        float marginLeft = lastMarginLeftValue + (MULTIPLIER * tabs);
        currentParagraph.setMarginLeft(marginLeft);
    }

    @Override
    public String toString() {
        return "token.command.Indent{" +
                "tabs=" + tabs +
                '}';
    }
}


