package expression.exceptions;


import expression.MiniExpression;

public class CheckedMultiply extends BinaryOperations {

    public CheckedMultiply(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        int tmp = x * y;
        if (x != 0 && y != 0 && (tmp / y != x || tmp / x != y)) {
            throw new OverflowException();
        }
        return x * y;
    }

    @Override
    protected String getType() {
        return "*";
    }

}
