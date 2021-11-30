import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.rits.cloning.Cloner;
import token.command.Command;
import token.ParagraphText;
import token.Token;

import java.io.FileNotFoundException;
import java.util.List;

public class Parser {
    private Document document;

    Parser(String outputFile) throws FileNotFoundException {
        this.document = new Document(new PdfDocument(new PdfWriter(outputFile)), PageSize.A4);
    }


    public void parse(List<Token> tokens) {
        Paragraph currentParagraph = new Paragraph();
        Text currentText = new Text("");
        for(Token token: tokens){
            if (token instanceof Command) ((Command) token).apply(document, currentParagraph, currentText);
            else if (token instanceof ParagraphText) {
                currentText.setText(((ParagraphText) token).getText() + " ");
                currentParagraph.add(currentText);
                currentText = new Cloner().deepClone(currentText);
            }
        }
        document.add(currentParagraph);
        document.close();
    }
}
