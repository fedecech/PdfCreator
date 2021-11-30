package token.command;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class NewParagraph extends Command {

    public NewParagraph(String lit){
        super(lit);
    }

    @Override
    public void apply(Document document, Paragraph currentParagraph, Text currentText) {
        document.add(currentParagraph);
        currentParagraph.getChildren().clear();
    }

    @Override
    public String toString() {
        return "token.command.NewParagraph{" +
                "value='" + getValue() + '\'' +
                ", lit='" + getLit() + '\'' +
                '}';
    }
}
