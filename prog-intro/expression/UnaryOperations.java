package expression;

import java.util.Objects;

public abstract class UnaryOperations implements MiniExpression {
    private final MiniExpression x;


    public UnaryOperations(MiniExpression x) {
        this.x = x;
    }

    protected abstract int count(int x);

    protected abstract String getType();

    @Override
    public int evaluate(int x) {
        return count(this.x.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(this.x.evaluate(x, y, z));
    }

    public String toString() {
        return getType() + "(" + x.toString() + ")";
    }

    public boolean equals(Object obj) {
        if (obj instanceof UnaryOperations newOperations) {
            return Objects.equals(getType(), newOperations.getType()) && Objects.equals(x, newOperations.x);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, getClass());
    }
}
