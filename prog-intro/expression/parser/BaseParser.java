package expression.parser;

import java.util.List;

public class BaseParser {
    private static final char END = '\0';
    private CharSource source;
    protected char ch;

    protected void changeSource(final CharSource source) {
        this.source = source;
        take();
    }

    protected void take() {
        ch = source.hasNext() ? source.next() : END;
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected String getParsedString() {
        return source.getBeginString();
    }

    protected String getEnd() {
        return source.getEndString();
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
}
