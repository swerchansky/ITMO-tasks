package expression.exceptions;

import expression.parser.BaseParser;
import expression.parser.StringSource;
import expression.MiniExpression;

public class ExpressionParser extends BaseParser implements Parser {
    private boolean checkUnaryMinus;
    private int checkOpeningBracket;
    private int checkClosingBracket;

    @Override
    public MiniExpression parse(String expression) throws ParsingException {
        changeSource(new StringSource(expression));
//        System.out.println(expression);
        checkUnaryMinus = true;
        checkOpeningBracket = 0;
        checkClosingBracket = 0;
        return parseExpression(null);
    }

    private MiniExpression parseExpression(MiniExpression parsed) throws ParsingException {
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

    private MiniExpression parseHigh(MiniExpression parsed) throws ParsingException {
        skipWhitespace();
        if (take('l')) {
            expect('0');
            if (!Character.isWhitespace(ch) && ch != '(') {
                throw new InvalidCharacterException(ch);
            }
            return new CheckedMaxBin(parseHigh(parsed));
        } else if (take('t')) {
            expect('0');
            if (!Character.isWhitespace(ch) && ch != '(') {
                throw new InvalidCharacterException(ch);
            }
            return new CheckedMinBin(parseHigh(parsed));
        } else if (take('m')) {
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
                return new CheckedNegate(parseHigh(null));
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

    private MiniExpression parseMid(MiniExpression parsed) throws ParsingException {
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

    private MiniExpression parseLow(MiniExpression parsed) throws ParsingException {
        skipWhitespace();
        if (take('+')) {
            if (parsed == null) {
                throw new MissingArgumentException(getParsedString(), getEnd());
            }
            checkUnaryMinus = true;
            MiniExpression newParsed = parseHigh(null);
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
            MiniExpression newParsed = parseHigh(null);
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


    private MiniExpression make(MiniExpression first, MiniExpression second, String type) throws ParsingException {
        if (second == null) {
            throw new MissingArgumentException(getParsedString(), getEnd());
        }
        switch (type) {
            case "+" -> {
                return new CheckedAdd(first, second);
            }
            case "-" -> {
                return new CheckedSubtract(first, second);
            }
            case "*" -> {
                return new CheckedMultiply(first, second);
            }
            case "/" -> {
                return new CheckedDivide(first, second);
            }
            case "min" -> {
                return new CheckedMin(first, second);
            }
            case "max" -> {
                return new CheckedMax(first, second);
            }
            default -> throw new InvalidCharacterException(ch);
        }
    }

    private MiniExpression parseVariable() throws ParsingException {
        switch (ch) {
            case 'x' -> {
                take();
                return new Variable("x");
            }
            case 'y' -> {
                take();
                return new Variable("y");
            }
            case 'z' -> {
                take();
                return new Variable("z");
            }
            default -> {
                throw new InvalidCharacterException(ch);
            }
        }
    }

    private MiniExpression parseConst(boolean type) throws ParsingException {
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
            int num = Integer.parseInt(str.toString());
            return new Const(num);
        } catch (NumberFormatException e) {
            throw new OverflowException();
        }
    }
}
