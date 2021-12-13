package expression;

import base.TestCounter;
import expression.common.BaseTester;
import expression.common.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ExpressionTest extends BaseTester {
    public static final Selector<?> SELECTOR = Selector.create(ExpressionTest.class, ExpressionTest::new, "easy", "hard")
            .variant("Triple", v(TripleExpression::tester))
            .variant("BigDecimal", v(BigDecimalExpression::tester))
            .variant("BigInteger", v(BigIntegerExpression::tester))
            .variant("Base", v(Expression::tester));

    private final List<BiFunction<TestCounter, Integer, ? extends ExpressionTester<?, ?, ?>>> testers = new ArrayList<>();

    public ExpressionTest(final TestCounter counter, final int mode) {
        super(counter, mode);
    }

    @Override
    protected void test() {
        for (final BiFunction<TestCounter, Integer, ? extends ExpressionTester<?, ?, ?>> tester : testers) {
            final ExpressionTester<?, ?, ?> test = tester.apply(counter, mode);
            counter.scope("Testing " + test.toString(), test::test);
        }
    }

    public static Consumer<? super ExpressionTest> v(final BiFunction<TestCounter, Integer, ? extends ExpressionTester<?, ?, ?>> tester) {
        return t -> t.testers.add(tester);
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }
}
