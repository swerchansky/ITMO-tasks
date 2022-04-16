package expression;

import base.TestCounter;
import expression.common.Node;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static expression.common.Node.constant;
import static expression.common.Node.op;

/**
 * One-argument arithmetic expression over longs.
 *
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@FunctionalInterface
@SuppressWarnings("ClassReferencesSubclass")
public interface BigIntegerExpression extends ToMiniString {
    Function<Object, Const> C = ExpressionTester.constant(BigInteger.class);

    BigInteger evaluate(BigInteger x);

    static ExpressionTester<?, ?, ?> tester(final TestCounter counter, final int mode) {
        final Variable vx = new Variable("x");
        final Node<BigInteger> nx = op("x");
        final Node<BigInteger> n1 = constant(BigInteger.ONE);
        final Node<BigInteger> n2 = constant(BigInteger.TWO);
        final Const c2 = C.apply(BigInteger.TWO);
        final Const c1 = C.apply(BigInteger.ONE);

        return new ExpressionTester<BigIntegerExpression, BigInteger, BigInteger>(
                counter,
                mode,
                BigIntegerExpression.class,
                BigIntegerExpression::evaluate,
                IntStream.rangeClosed(-10, 10).mapToObj(BigInteger::valueOf).collect(Collectors.toList()),
                random -> BigInteger.valueOf(random.getRandom().nextLong()),
                random -> BigInteger.valueOf(random.getRandom().nextLong()),
                c -> x -> c, BigInteger.class,
                (op, a, b) -> x -> op.apply(a.evaluate(x), b.evaluate(x)),
                BigInteger::add, BigInteger::subtract, BigInteger::multiply, BigInteger::divide,
                ExpressionTester.variable("x", x -> x)
        )
                .basic("10", "10", x -> BigInteger.TEN, C.apply(BigInteger.TEN))
                .basic("x", "x", x -> x, vx)
                .basic(op("+", nx, n2), new Add(vx, c2))
                .basic(op("-", n1, nx), new Subtract(c1, vx))
                .basic(op("*", n2, nx), new Multiply(c2, vx))
                .basic(op("+", nx, nx), new Add(vx, vx))
                .basic(op("/", nx, n2), new Divide(vx, c2));
    }
}
