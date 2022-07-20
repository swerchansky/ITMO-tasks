package expression.exceptions;


import expression.MiniExpression;

public class CheckedNegate extends UnaryOperations {

    public CheckedNegate(MiniExpression x) {
        super(x);
    }

    @Override
    protected int count(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -1 * x;
    }

    @Override
    protected String getType() {
        return "-";
    }

}