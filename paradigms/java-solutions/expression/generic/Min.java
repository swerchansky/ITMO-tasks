package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Min<T> extends BinaryOperations<T> {

    public Min(GExpression<T> first, GExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T count(T x, T y, ArithmeticTabulator<T> tabulator) {
        return tabulator.min(x, y);
    }

    @Override
    protected String getType() {
        return "min";
    }

}
