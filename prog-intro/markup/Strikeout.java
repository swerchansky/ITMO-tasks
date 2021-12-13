package markup;

import java.util.List;

public class Strikeout extends AbstractConstructor {
    public Strikeout(List<MarkElement> inputList) {
        super(inputList);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        super.symbol = "~";
        super.toMarkdown(out);
    }

    @Override
    public void toBBCode(StringBuilder out) {
        super.symbol = "s";
        super.toBBCode(out);
    }
}
