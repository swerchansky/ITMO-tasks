package expression.parser;

import expression.*;
import expression.exceptions.DivisionByZeroException;

public class ExpressionParser extends BaseParser implements Parser {
    private boolean checkUnaryMinus;
    private int checkOpeningBracket;
    private int checkClosingBracket;

    @Override
    public MiniExpression parse(String expression){
        changeSource(new StringSource(expression));
        checkUnaryMinus = true;
        checkOpeningBracket = 0;
        checkClosingBracket = 0;
        return parseExpression(null);
    }

    private MiniExpression parseExpression(MiniExpression parsed){
        skipWhitespace();
        while (!eof()) {
            if (ch == ')' && checkOpeningBracket < checkClosingBracket + 1) {
                take();
            } else if (ch == ')') {
                return parsed;
            }
            parsed = parseHigh(parsed);
        }
        return parsed;
    }

    private MiniExpression parseHigh(MiniExpression parsed) {
        skipWhitespace();
        if (take('l')) {
            expect('0');
            return new MaxBin(parseHigh(parsed));
        } else if (take('t')) {
            expect('0');
            return new MinBin(parseHigh(parsed));
        } else if (take('m')) {
            checkUnaryMinus = true;
            if (take('i')) {
                take('n');
                return make(parsed, parseHigh(null), "min");
            } else {
                take('a');
                take('x');
                return make(parsed, parseHigh(null), "max");
            }
        } else if (take('(')) {
            checkOpeningBracket++;
            parsed = parseExpression(parsed);
            skipWhitespace();
            if (!take(')')) {
                take();
            }
            checkClosingBracket++;
            return parsed;
        } else if (checkUnaryMinus && take('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                checkUnaryMinus = false;
                return parseConst(true);
            } else {
                return new Unary(parseHigh(null));
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

    private MiniExpression parseMid(MiniExpression parsed) {
        skipWhitespace();
        if (take('*')) {
            checkUnaryMinus = true;
            MiniExpression newParsed = parseHigh(null);
            skipWhitespace();
            newParsed = make(parsed, newParsed, "*");
            if (ch == '-' || ch == '+') {
                return newParsed;
            }
            return parseMid(newParsed);
        } else if (take('/')) {
            checkUnaryMinus = true;
            MiniExpression newParsed = parseHigh(null);
            skipWhitespace();
            newParsed = make(parsed, newParsed, "/");
            if (ch == '-' || ch == '+') {
                return newParsed;
            }
            return parseMid(newParsed);
        }
        return parseLow(parsed);
    }

    private MiniExpression parseLow(MiniExpression parsed){
        skipWhitespace();
        if (take('+')) {
            checkUnaryMinus = true;
            return make(parsed, parseExpression(null), "+");
        } else if (take('-')) {
            checkUnaryMinus = true;
            skipWhitespace();
            MiniExpression newParsed = parseHigh(null);
            skipWhitespace();
            if (ch != '-' && ch != '+') {
                return make(parsed, parseHigh(newParsed), "-");
            }
            return make(parsed, newParsed, "-");
        }
        return parseExpression(parsed);
    }


    private MiniExpression make(MiniExpression first, MiniExpression second, String type){
        switch (type) {
            case "+" -> {
                return new Add(first, second);
            }
            case "-" -> {
                return new Subtract(first, second);
            }
            case "*" -> {
                return new Multiply(first, second);
            }
            case "/" -> {
                return new Divide(first, second);
            }
            default -> throw new DivisionByZeroException();
        }
    }

    private MiniExpression parseVariable() {
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
                throw new DivisionByZeroException();
            }
        }
    }

    private MiniExpression parseConst(boolean type) {
        StringBuilder str = new StringBuilder();
        if (type) {
            str.append('-');
        }
        while (between('0', '9')) {
            str.append(ch);
            take();
        }
        skipWhitespace();
        int num = Integer.parseInt(str.toString());
        return new Const(num);
    }
}
