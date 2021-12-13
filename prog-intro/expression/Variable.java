package expression;

import java.util.Objects;

public class Variable implements MiniExpression {
    private final String x;

    public Variable(String x) {
        this.x = x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (this.x.equals("x")) {
            return x;
        } else if (this.x.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    public String toString() {
        return x;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Variable newOperations) {
            return Objects.equals(x, newOperations.x);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hashCode(x);
    }

}
