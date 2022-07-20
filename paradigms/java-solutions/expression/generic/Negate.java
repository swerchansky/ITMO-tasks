package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Negate<T> extends UnaryOperations<T> {

    public Negate(GExpression<T> x) {
        super(x);
    }

    @Override
    protected T count(T x, ArithmeticTabulator<T> tabulator) {
        return tabulator.negate(x);
    }


    @Override
    protected String getType() {
        return "-";
    }

}
