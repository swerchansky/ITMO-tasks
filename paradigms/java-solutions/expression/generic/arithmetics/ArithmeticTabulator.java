package expression.generic.arithmetics;

public interface ArithmeticTabulator<T> {
    T add(T x, T y);
    T subtract(T x, T y);
    T divide(T x, T y);
    T multiply(T x, T y);
    T negate(T x);
    T value(int x);
    T parseValue(String x);
    T count(T x);
    T min(T x, T y);
    T max(T x, T y);
}
