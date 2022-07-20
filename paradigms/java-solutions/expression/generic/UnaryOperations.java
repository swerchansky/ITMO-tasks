package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public abstract class UnaryOperations<T> implements GExpression<T> {
    private final GExpression<T> x;


    public UnaryOperations(GExpression<T> x) {
        this.x = x;
    }

    protected abstract T count(T x, ArithmeticTabulator<T> tabulator);

    protected abstract String getType();

    @Override
    public T evaluate(T x, T y, T z, ArithmeticTabulator<T> tabulator) {
        return count(this.x.evaluate(x, y, z, tabulator), tabulator);
    }

    public String toString() {
        return getType() + "(" + x.toString() + ")";
    }

}
