package expression.generic;

import expression.GExpression;
import expression.generic.arithmetics.ArithmeticTabulator;

public class Const<T> implements GExpression<T> {
    private final String cnst;

    public Const(String cnst) {
        this.cnst = cnst;
    }

    @Override
    public T evaluate(T x, T y, T z, ArithmeticTabulator<T> tabulator) {
        return tabulator.parseValue(cnst);
    }

    public String toString() {
        return String.valueOf(cnst);
    }

}
