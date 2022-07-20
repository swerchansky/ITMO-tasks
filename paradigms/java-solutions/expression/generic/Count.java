package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Count<T> extends UnaryOperations<T> {

    public Count(GExpression<T> x) {
        super(x);
    }

    @Override
    protected T count(T x, ArithmeticTabulator<T> tabulator) {
        return tabulator.count(x);
    }

    @Override
    protected String getType() {
        return "count";
    }

}
