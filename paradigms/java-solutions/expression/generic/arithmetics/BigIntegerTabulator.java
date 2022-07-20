package expression.generic.arithmetics;

import expression.exceptions.DivisionByZeroException;

import java.math.BigInteger;

public class BigIntegerTabulator implements ArithmeticTabulator<BigInteger> {

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        if (y.compareTo(BigInteger.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        return x.divide(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger value(int x) {
        return BigInteger.valueOf(x);
    }

    @Override
    public BigInteger parseValue(String x) {
        return new BigInteger(x);
    }

    @Override
    public BigInteger count(BigInteger x) {
        return BigInteger.valueOf(x.bitCount());
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        return x.min(y);
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        return x.max(y);
    }

}
