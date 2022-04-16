package markup;

import java.util.List;

public class Paragraph implements MarkElement {
    List<MarkElement> text;

    public Paragraph(List<MarkElement> inputList) {
        text = inputList;
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        for (MarkElement markElement : text) {
            markElement.toMarkdown(out);
        }
    }

    @Override
    public void toBBCode(StringBuilder out) {
        for (MarkElement markElement : text) {
            markElement.toBBCode(out);
        }
    }
}
