package expression;

import expression.generic.arithmetics.ArithmeticTabulator;

public interface GExpression<T> extends ToMiniString {
    T evaluate(T x, T y, T z, ArithmeticTabulator<T> tabulator);
}
