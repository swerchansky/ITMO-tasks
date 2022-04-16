package expression;

import base.TestCounter;
import expression.common.Node;

import java.math.BigDecimal;
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
public interface BigDecimalExpression extends ToMiniString {
    Function<Object, Const> C = ExpressionTester.constant(BigDecimal.class);

    BigDecimal evaluate(BigDecimal x);

    static ExpressionTester<?, ?, ?> tester(final TestCounter counter, final int mode) {
        final Variable vx = new Variable("x");
        final Node<BigDecimal> nx = op("x");

        final Node<BigDecimal> n1 = constant(BigDecimal.ONE);
        final Node<BigDecimal> n10 = constant(BigDecimal.TEN);
        final Const c1 = C.apply(BigDecimal.ONE);
        final Const c10 = C.apply(BigDecimal.TEN);

        return new ExpressionTester<>(
                counter,
                mode,
                BigDecimalExpression.class,
                BigDecimalExpression::evaluate,
                IntStream.rangeClosed(-10, 10).mapToObj(v -> new BigDecimal(v + ".000")).collect(Collectors.toList()),
                random -> BigDecimal.valueOf(random.getRandom().nextGaussian()),
                random -> BigDecimal.valueOf(random.getRandom().nextGaussian()),
                c -> x -> c, BigDecimal.class,
                (op, a, b) -> x -> op.apply(a.evaluate(x), b.evaluate(x)),
                BigDecimal::add, BigDecimal::subtract, BigDecimal::multiply, BigDecimal::divide,
                ExpressionTester.variable("x", x -> x)
        )
                .basic("10", "10", x -> BigDecimal.TEN, C.apply(BigDecimal.TEN))
                .basic("x", "x", x -> x, vx)
                .basic(op("+", nx, n10), new Add(vx, c10))
                .basic(op("-", n1, nx), new Subtract(c1, vx))
                .basic(op("*", n10, nx), new Multiply(c10, vx))
                .basic(op("+", nx, nx), new Add(vx, vx))
                .basic(op("/", nx, n10), new Divide(vx, c10));
    }
}
