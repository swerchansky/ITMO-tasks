package expression.parser;


public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public String getBeginString() {
        if (pos == data.length()) {
            return data;
        }
        if (pos - 2 < 0) {
            return "";
        }
        return data.substring(0, pos - 2);
    }

    @Override
    public String getEndString() {
        if (pos == data.length()) {
            return "";
        }
        if (pos - 2 < 0) {
            return data;
        }
        return data.substring(pos - 2);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        return new IllegalArgumentException(pos + ": " + message);
    }
}
