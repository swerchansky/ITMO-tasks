package expression;

public class Add extends BinaryOperations {

    public Add(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        return x + y;
    }

    @Override
    protected char getType() {
        return '+';
    }

}
