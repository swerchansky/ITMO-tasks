package markup;

import java.util.List;

public abstract class AbstractConstructor implements MarkElement {
    protected List<MarkElement> text;
    protected String symbol;

    public AbstractConstructor(List<MarkElement> inputList) {
        text = inputList;
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(symbol);
        for (MarkElement markElement : text) {
            markElement.toMarkdown(out);
        }
        out.append(symbol);
    }

    @Override
    public void toBBCode(StringBuilder out) {
        out.append("[").append(symbol).append("]");
        for (MarkElement markElement : text) {
            markElement.toBBCode(out);
        }
        out.append("[/").append(symbol).append("]");
    }
}
