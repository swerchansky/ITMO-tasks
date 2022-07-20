package expression.exceptions;


import expression.MiniExpression;

public class CheckedMax extends BinaryOperations {

    public CheckedMax(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    @Override
    protected String getType() {
        return "max";
    }
}