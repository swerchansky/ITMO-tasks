package expression.exceptions;

public class InvalidNumberException extends ParsingException {
    public InvalidNumberException(String parsed, String last) {
        super("Whitespace between number: " + parsed + " " + '\u032D' + " " + last);
    }
}