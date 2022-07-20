package expression.parser;


import expression.exceptions.*;
//import expression.generic.*;
import expression.GExpression;
import expression.generic.*;
import expression.generic.Add;
import expression.generic.Const;
import expression.generic.Divide;
import expression.generic.Multiply;
import expression.generic.Subtract;
import expression.generic.Variable;


public class ExpressionParser<T> extends BaseParser {
    private boolean checkUnaryMinus;
    private int checkOpeningBracket;
    private int checkClosingBracket;

    public GExpression<T> parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
        checkUnaryMinus = true;
        checkOpeningBracket = 0;
        checkClosingBracket = 0;
        return parseExpression(null);
    }

    private GExpression<T> parseExpression(GExpression<T> parsed) throws ParsingException {
        skipWhitespace();
        while (!eof()) {
            if (ch == ')' && checkOpeningBracket < checkClosingBracket + 1) {
                take();
                throw new BracketsException("Missing opening bracket");
            } else if (ch == ')') {
                return parsed;
            }
            parsed = parseHigh(parsed);
        }
        return parsed;
    }

    private GExpression<T> parseHigh(GExpression<T> parsed) throws ParsingException {
        skipWhitespace();
        if (take('m')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            if (take('i')) {
                if (!take('n')) {
                    throw new MissingArgumentException(getParsedString(), getEnd());
                }
                if (eof()) {
                    throw new MissingArgumentException(getParsedString(), getEnd());
                }
                if (!Character.isWhitespace(ch) && ch != '(' && ch != '-') {
                    throw new InvalidCharacterException(ch);
                }
                return make(parsed, parseHigh(null), "min");
            } else if (take('a')) {
                if (!take('x')) {
                    throw new MissingArgumentException(getParsedString(), getEnd());
                }
                if (eof()) {
                    throw new MissingArgumentException(getParsedString(), getEnd());
                }
                if (!Character.isWhitespace(ch) && ch != '(' && ch != '-') {
                    throw new InvalidCharacterException(ch);
                }
                return make(parsed, parseHigh(null), "max");
            } else {
                throw new InvalidCharacterException(ch);
            }
        } else if (take('c')) {
            expect("ount");
            skipWhitespace();
            return new Count<>(parseHigh(null));
        } else if (take('(')) {
            checkOpeningBracket++;
            parsed = parseExpression(parsed);
            if (parsed == null) {
                throw new BracketsException("Brackets can't be empty");
            }
            skipWhitespace();
            if (!take(')')) {
                take();
                throw new BracketsException("Missing closing bracket");
            }
            checkClosingBracket++;
            return parsed;
        } else if (checkUnaryMinus && take('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                checkUnaryMinus = false;
                return parseConst(true);
            } else {
                return new Negate<>(parseHigh(null));
            }
        } else if (between('0', '9')) {
            checkUnaryMinus = false;
            return parseConst(false);
        } else if (Character.isLetter(ch)) {
            checkUnaryMinus = false;
            return parseVariable();
        } else {
            return parseMid(parsed);
        }
    }

    private GExpression<T> parseMid(GExpression<T> parsed) throws ParsingException {
        skipWhitespace();
        if (take('*')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            return make(parsed, parseHigh(null), "*");
        } else if (take('/')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            return make(parsed, parseHigh(null), "/");
        }
        return parseLow(parsed);
    }

    private GExpression<T> parseLow(GExpression<T> parsed) throws ParsingException {
        skipWhitespace();
        if (take('+')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            GExpression<T> newParsed = parseHigh(null);
            skipWhitespace();
            if (ch != '-' && ch != '+') {
                return make(parsed, parseExpression(newParsed), "+");
            }
            return make(parsed, newParsed, "+");
        } else if (take('-')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            GExpression<T> newParsed = parseHigh(null);
            skipWhitespace();
            if (ch != '-' && ch != '+') {
                return make(parsed, parseExpression(newParsed), "-");
            }
            return make(parsed, newParsed, "-");
        } else if (ch != ')' && !eof()) {
            throw new InvalidCharacterException(ch);
        } else if (eof()) {
            throw new MissingArgumentException(getParsedString(), getEnd());
        }
        return parseExpression(parsed);
    }


    private GExpression<T> make(GExpression<T> first, GExpression<T> second, String type) throws ParsingException {
        if (second == null) {
            throw new MissingArgumentException(getParsedString(), getEnd());
        }
        switch (type) {
            case "+" -> {
                return new Add<>(first, second);
            }
            case "-" -> {
                return new Subtract<>(first, second);
            }
            case "*" -> {
                return new Multiply<>(first, second);
            }
            case "/" -> {
                return new Divide<>(first, second);
            }
            case "min" -> {
                return new Min<>(first, second);
            }
            case "max" -> {
                return new Max<>(first, second);
            }
            case "count" -> {
                return new Count<>(first);
            }
            default -> throw new InvalidCharacterException(ch);
        }
    }

    private GExpression<T> parseVariable() throws ParsingException {
        switch (ch) {
            case 'x' -> {
                take();
                return new Variable<>("x");
            }
            case 'y' -> {
                take();
                return new Variable<>("y");
            }
            case 'z' -> {
                take();
                return new Variable<>("z");
            }
            default -> {
                throw new InvalidCharacterException(ch);
            }
        }
    }

    private GExpression<T> parseConst(boolean type) throws ParsingException {
        StringBuilder str = new StringBuilder();
        if (type) {
            str.append('-');
        }
        while (between('0', '9')) {
            str.append(ch);
            take();
        }
        skipWhitespace();
        if (between('0', '9'))
            throw new InvalidNumberException(getParsedString(), getEnd());
        try {
            return new Const<>(str.toString());
        } catch (NumberFormatException e) {
            throw new OverflowException();
        }
    }
}
