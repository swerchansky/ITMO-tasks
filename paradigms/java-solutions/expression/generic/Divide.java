package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Divide<T> extends BinaryOperations<T> {

    public Divide(GExpression<T> first, GExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T count(T x, T y, ArithmeticTabulator<T> tabulator) {
        return tabulator.divide(x, y);
    }

    @Override
    protected String getType() {
        return "/";
    }

}
