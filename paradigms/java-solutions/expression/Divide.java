package expression;

public class Divide extends BinaryOperations {

    public Divide(MiniExpression first, MiniExpression second) {
        super(first, second);
    }

    @Override
    protected int count(int x, int y) {
        return x / y;
    }

    @Override
    protected String getType() {
        return "/";
    }

}
