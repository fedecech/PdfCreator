package token.command;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.util.HashMap;
import java.util.Map;

public class ApplyStyle extends Command {
    enum TextStyle{
        BOLD,
        ITALICS,
        REGULAR,
        LARGE,
        NORMAL
    }

    private TextStyle textStyle;
    private static final Map<String, TextStyle> valueStyleTable = new HashMap<>(){{
        put("bold", TextStyle.BOLD);
        put("italics", TextStyle.ITALICS);
        put("normal", TextStyle.NORMAL);
        put("regular", TextStyle.REGULAR);
        put("large", TextStyle.LARGE);
    }};
    private static final float LARGE_SIZE = 20;
    private static final float NORMAL_SIZE = 10;
    private static final int BOLD_PROPERTY = 8;
    private static final int ITALICS_PROPERTY = 31;

    public ApplyStyle(String lit){
        super(lit);
        this.textStyle = valueStyleTable.get(getValue());
    }

    @Override
    public void apply(Document document, Paragraph currentParagraph, Text currentText) {
        switch (textStyle){
            case BOLD:
                currentText.setBold();
                break;
            case ITALICS:
                currentText.setItalic();
                break;
            case LARGE:
                currentText.setFontSize(LARGE_SIZE);
                break;
            case NORMAL:
                currentText.setFontSize(NORMAL_SIZE);
                break;
            case REGULAR:
                currentText.setProperty(BOLD_PROPERTY, false);
                currentText.setProperty(ITALICS_PROPERTY, false);
                currentText.setFontSize(NORMAL_SIZE);
                break;

        }
    }

    @Override
    public String toString() {
        return "token.command.ApplyStyle{" +
                "textStyle=" + textStyle +
                '}';
    }
}