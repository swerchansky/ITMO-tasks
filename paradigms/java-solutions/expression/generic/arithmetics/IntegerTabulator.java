package expression.generic.arithmetics;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.OverflowException;

public class IntegerTabulator implements ArithmeticTabulator<Integer> {

    @Override
    public Integer add(Integer x, Integer y) {
        if (x < 0 && y < 0 && x + y >= 0) {
            throw new OverflowException();
        } else if (x > 0 && y > 0 && x + y < 0) {
            throw new OverflowException();
        }
        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if (x < 0 && y > 0 && x - y > 0) {
            throw new OverflowException();
        } else if (x >= 0 && y <= 0 && x - y < 0) {
            throw new OverflowException();
        }
        return x - y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DivisionByZeroException();
        } else if (x / y < 0 && x < 0 && y < 0) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        int tmp = x * y;
        if (x != 0 && y != 0 && (tmp / y != x || tmp / x != y)) {
            throw new OverflowException();
        }
        return x * y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

    @Override
    public Integer value(int x) {
        return x;
    }

    @Override
    public Integer parseValue(String x) {
        return Integer.parseInt(x);
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Math.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Math.max(x, y);
    }

}
