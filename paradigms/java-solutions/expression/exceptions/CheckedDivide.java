package expression.exceptions;


import expression.MiniExpression;

public class CheckedDivide extends BinaryOperations {

    public CheckedDivide(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        if (y == 0) {
            throw new DivisionByZeroException();
        } else if (x / y < 0 && x < 0 && y < 0) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    protected String getType() {
        return "/";
    }

}

