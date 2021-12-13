package markup;

public interface MarkElement {
    void toMarkdown(StringBuilder out);
    void toBBCode(StringBuilder out);
}

