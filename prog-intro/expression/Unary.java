package expression;

public class Unary extends UnaryOperations {

    public Unary(MiniExpression x) {
        super(x);
    }

    @Override
    protected int count(int x) {
        return -1 * x;
    }

    @Override
    protected String getType() {
        return "-";
    }

}
