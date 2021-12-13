package expression;

public class Subtract extends BinaryOperations {

    public Subtract(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        return x - y;
    }

    @Override
    protected char getType() {
        return '-';
    }

}
