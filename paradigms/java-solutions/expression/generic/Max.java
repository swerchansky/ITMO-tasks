package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Max<T> extends BinaryOperations<T> {

    public Max(GExpression<T> first, GExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T count(T x, T y, ArithmeticTabulator<T> tabulator) {
        return tabulator.max(x, y);
    }

    @Override
    protected String getType() {
        return "max";
    }

}
