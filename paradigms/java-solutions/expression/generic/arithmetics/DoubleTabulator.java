package expression.generic.arithmetics;

public class DoubleTabulator implements ArithmeticTabulator<Double> {

    @Override
    public Double add(Double x, Double y) {
        return x + y;
    }

    @Override
    public Double subtract(Double x, Double y) {
        return x - y;
    }

    @Override
    public Double divide(Double x, Double y) {
        return x / y;
    }

    @Override
    public Double multiply(Double x, Double y) {
        return x * y;
    }

    @Override
    public Double negate(Double x) {
        return -x;
    }

    @Override
    public Double value(int x) {
        return (double) x;
    }

    @Override
    public Double parseValue(String x) {
        return Double.parseDouble(x);
    }

    @Override
    public Double count(Double x) {
        return (double) Long.bitCount(Double.doubleToLongBits(x));
    }

    @Override
    public Double min(Double x, Double y) {
        return Math.min(x, y);
    }

    @Override
    public Double max(Double x, Double y) {
        return Math.max(x, y);
    }

}
