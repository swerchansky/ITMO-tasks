package expression;

public class Multiply extends BinaryOperations {

    public Multiply(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        return x * y;
    }

    @Override
    protected char getType() {
        return '*';
    }

}
