package expression.exceptions;

import expression.MiniExpression;

import java.util.Objects;

public abstract class BinaryOperations implements MiniExpression {
    private final MiniExpression first;
    private final MiniExpression second;

    public BinaryOperations(MiniExpression first, MiniExpression second) {
        this.first = first;
        this.second = second;
    }


    protected abstract int count(int x, int y);

    protected abstract String getType();

    @Override
    public int evaluate(int x) {
        return count(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return count(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    public String toString() {
        return "(" + first + ' ' + getType() + ' ' + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryOperations newOperations) {
            return Objects.equals(getType(), newOperations.getType()) &&
                    Objects.equals(first, newOperations.first) && Objects.equals(second, newOperations.second);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, getClass());
    }

}
