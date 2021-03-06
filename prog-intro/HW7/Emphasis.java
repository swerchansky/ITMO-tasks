package markup;

import java.util.List;

public class Emphasis extends AbstractConstructor{
    public Emphasis(List<MarkElement> inputList) {
        super(inputList);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        super.symbol = "*";
        super.toMarkdown(out);
    }

    @Override
    public void toBBCode(StringBuilder out) {
        super.symbol = "i";
        super.toBBCode(out);
    }
}
