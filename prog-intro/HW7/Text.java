package markup;

public class Text implements MarkElement {
    private final String text;

    public Text(String inputStr) {
        text = inputStr;
    }

    @Override
    public void toMarkdown(StringBuilder out) {
        out.append(text);
    }

    @Override
    public void toBBCode(StringBuilder out) {
        out.append(text);
    }
}
