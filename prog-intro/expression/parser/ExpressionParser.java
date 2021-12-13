package expression.parser;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {
    private boolean checkUnaryMinus;

    @Override
    public MiniExpression parse(String expression) {
        changeSource(new StringSource(expression));
        checkUnaryMinus = true;
        return parseExpression(null);
    }

    private MiniExpression parseExpression(MiniExpression parsed) {
        skipWhitespace();
        while (!eof()) {
            if (ch == ')') {
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
        } else if (take('(')) {
            parsed = parseExpression(parsed);
            skipWhitespace();
            take(')');
            return parsed;
        } else if (checkUnaryMinus && take('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                checkUnaryMinus = false;
                return parseConst(true);
            } else {
                return new Unary(parseHigh(parsed));
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
            return make(parsed, parseHigh(parsed), '*');
        } else if (take('/')) {
            checkUnaryMinus = true;
            return make(parsed, parseHigh(parsed), '/');
        }
        return parseLow(parsed);
    }

    private MiniExpression parseLow(MiniExpression parsed) {
        skipWhitespace();
        if (take('+')) {
            checkUnaryMinus = true;
            return make(parsed, parseExpression(parsed), '+');
        } else if (take('-')) {
            checkUnaryMinus = true;
            skipWhitespace();
            if (ch != '-') {
                return make(parsed, parseExpression(parsed), '-');
            }
            return make(parsed, parseHigh(parsed), '-');
        }
        return parseExpression(parsed);
    }


    private MiniExpression make(MiniExpression first, MiniExpression second, char type) {
        switch (type) {
            case '+' -> {
                return new Add(first, second);
            }
            case '-' -> {
                return new Subtract(first, second);
            }
            case '*' -> {
                return new Multiply(first, second);
            }
            case '/' -> {
                return new Divide(first, second);
            }
            default -> {
                throw error("Invalid operation");
            }
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
                throw error("Invalid variable");
            }
        }
    }

    private MiniExpression parseConst(boolean type) {
        StringBuilder num = new StringBuilder();
        if (type) {
            num.append('-');
        }
        while (between('0', '9')) {
            num.append(ch);
            take();
        }
        return new Const(Integer.parseInt(num.toString()));
    }
}
