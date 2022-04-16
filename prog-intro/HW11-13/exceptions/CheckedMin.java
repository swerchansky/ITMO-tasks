package expression.exceptions;

import expression.MiniExpression;

public class CheckedMin extends BinaryOperations {

    public CheckedMin(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        if (x < y) {
            return x;
        }
        return y;
    }

    @Override
    protected String getType() {
        return "min";
    }
}
