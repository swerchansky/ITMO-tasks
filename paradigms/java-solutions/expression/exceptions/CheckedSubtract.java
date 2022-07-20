package expression.exceptions;


import expression.MiniExpression;

public class CheckedSubtract extends BinaryOperations {

    public CheckedSubtract(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        if (x < 0 && y > 0 && x - y > 0) {
            throw new OverflowException();
        } else if (x >= 0 && y <= 0 && x - y < 0) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    protected String getType() {
        return "-";
    }

}
