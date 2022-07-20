package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Subtract<T> extends BinaryOperations<T> {

    public Subtract(GExpression<T> first, GExpression<T> second) {
        super(first, second);
    }

    @Override
    protected T count(T x, T y, ArithmeticTabulator<T> tabulator) {
        return tabulator.subtract(x, y);
    }

    @Override
    protected String getType() {
        return "-";
    }

}
