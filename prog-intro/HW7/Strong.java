package markup;

import java.util.List;

public class Strong extends AbstractConstructor{
    public Strong(List<MarkElement> inputList) {
        super(inputList);
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        super.symbol = "__";
        super.toMarkdown(out);
    }

    @Override
    public void toBBCode(StringBuilder out) {
        super.symbol = "b";
        super.toBBCode(out);
    }
}
