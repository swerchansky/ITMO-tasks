package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Multiply<T> extends BinaryOperations<T> {

    public Multiply(GExpression<T> first, GExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T count(T x, T y, ArithmeticTabulator<T> tabulator) {
        return tabulator.multiply(x, y);
    }

    @Override
    protected String getType() {
        return "*";
    }

}
