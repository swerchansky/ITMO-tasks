package expression.exceptions;

public class MissingArgumentException extends ParsingException{
    public MissingArgumentException(String parsed, String last) {
        super("Missing argument: " + parsed + " " + '\u032D' + "  " + last);
    }
}
