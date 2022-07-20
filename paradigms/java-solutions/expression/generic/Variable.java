package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Variable<T> implements GExpression<T> {
    private final String x;

    public Variable(String x) {
        this.x = x;
    }

    @Override
    public T evaluate(T x, T y, T z, ArithmeticTabulator<T> tabulator) {
        if (this.x.equals("x")) {
            return x;
        } else if (this.x.equals("y")) {
            return y;
        } else {
            return z;
        }
    }

    public String toString() {
        return x;
    }

}
