package expression.parser;

import expression.ToMiniString;
import expression.common.ExpressionKind;
import expression.common.Reason;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class Operations {
    public static final Operation NEGATE = unary("-", a -> -a);
    @SuppressWarnings("Convert2MethodRef")
    public static final Operation ADD       = binary("+", 1600, (a, b) -> a + b);
    public static final Operation SUBTRACT  = binary("-", 1602, (a, b) -> a - b);
    public static final Operation MULTIPLY  = binary("*", 2001, (a, b) -> a * b);
    public static final Operation DIVIDE    = binary("/", 2002, (a, b) -> b == 0 ? Reason.DBZ.error() : a / b);

    @SuppressWarnings("IntegerMultiplicationImplicitCastToLong")
    public static final Operation SHIFT_L = binary("<<", 1202, (a, b) -> (int) a << (int) b);
    public static final Operation SHIFT_A = binary(">>", 1202, (a, b) -> (int) a >> (int) b);
    public static final Operation SHIFT_R = binary(">>>", 1202, (a, b) -> (int) a >>> (int) b);

    public static final Operation L_ZEROES = unary("l0", v -> Integer.numberOfLeadingZeros((int) v));
    public static final Operation T_ZEROES = unary("t0", v -> Integer.numberOfTrailingZeros((int) v));

    public static final Operation MIN = binary("min", 401, Math::min);
    public static final Operation MAX = binary("max", 401, Math::max);

    private static final Reason INVALID_POW = new Reason("Invalid power");
    public static final Operation POW = binary("**", 2402, (a, b) -> {
        if (b < 0 || a == 0 && b == 0) {
            return INVALID_POW.error();
        }
        if (Math.abs(a) > 1 && b > 32) {
            return Reason.OVERFLOW.error();
        }
        final BigInteger result = BigInteger.valueOf(a).pow((int) b);
        if (result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0 || BigInteger.valueOf(Integer.MAX_VALUE).compareTo(result) < 0) {
            return Reason.OVERFLOW.error();
        }
        return result.intValue();
    });

    private static final Reason INVALID_LOG = new Reason("Invalid log");
    public static final Operation LOG = binary("//", 2402, (a, b) -> {
        if (a <= 0 || b <= 1) {
            return INVALID_LOG.error();
        }
        return (int) (Math.log(a) / Math.log(b));
    });

    public static final Operation ABS = unary("abs", Math::abs);

    private Operations() {
    }

    @FunctionalInterface
    public interface Operation extends Consumer<ParserTester> {}

    public static Operation unary(final String name, final LongUnaryOperator op) {
        return tests -> tests.unary(name, op);
    }

    public static Operation binary(final String name, final int priority, final LongBinaryOperator op) {
        return tests -> tests.binary(name, priority, op);
    }

    public static <E extends ToMiniString, C> Operation kind(
            final ExpressionKind<E, C> kind,
            final ParserTestSet.Parser<E> parser
    ) {
        return factory -> factory.kind(kind, parser);
    }
}
