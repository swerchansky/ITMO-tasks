package expression.exceptions;

public class InvalidCharacterException extends ParsingException{
    public InvalidCharacterException(char x) {
        super("Invalid character: " + x);
    }
}
