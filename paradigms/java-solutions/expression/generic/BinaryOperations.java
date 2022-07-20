package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public abstract class BinaryOperations<T> implements GExpression<T> {
    private final GExpression<T> first;
    private final GExpression<T> second;
    int count = 0;

    public BinaryOperations(GExpression<T> first, GExpression<T> second) {
        this.first = first;
        this.second = second;
    }

    protected abstract T count(T x, T y, ArithmeticTabulator<T> tabulator);

    protected abstract String getType();

    @Override
    public T evaluate(T x, T y, T z, ArithmeticTabulator<T> tabulator) {
        return count(first.evaluate(x, y, z, tabulator), second.evaluate(x, y, z, tabulator), tabulator);
    }

    public String toString() {
        return "(" + first + ' ' + getType() + ' ' + second + ")";
    }

}
