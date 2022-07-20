package expression.parser;


public interface CharSource {
    boolean hasNext();
    char next();
    String getBeginString();
    String getEndString();
    IllegalArgumentException error(final String message);
}
