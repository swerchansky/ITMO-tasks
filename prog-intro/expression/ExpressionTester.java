package expression;

import base.Asserts;
import base.ExtendedRandom;
import base.Pair;
import base.TestCounter;
import expression.common.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static base.Asserts.assertEquals;
import static base.Asserts.assertTrue;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class ExpressionTester<E extends ToMiniString, V, C> extends BaseTester {
    private final Class<E> type;
    private final BiFunction<E, V, Object> evaluate;
    private final List<V> values;
    private final Function<ExtendedRandom, V> randomVars;

    private final List<Test> basic = new ArrayList<>();
    private final List<Test> advanced = new ArrayList<>();
    private final Generator generator;

    private final List<Pair<ToMiniString, String>> prev = new ArrayList<>();

    @SafeVarargs
    protected ExpressionTester(
            final TestCounter counter,
            final int mode,
            final Class<E> expressionType,
            final BiFunction<E, V, Object> evaluate,
            final List<V> values,
            final Function<ExtendedRandom, C> randomValue,
            final Function<ExtendedRandom, V> randomVars,
            final Function<C, E> expectedConstant,
            final Class<?> constType,
            final Binary<C, E> binary,
            final BinaryOperator<C> add,
            final BinaryOperator<C> sub,
            final BinaryOperator<C> mul,
            final BinaryOperator<C> div,
            final Op<E>... vars
    ) {
        super(counter, mode);
        type = expressionType;
        this.evaluate = evaluate;
        this.values = values;
        this.randomVars = randomVars;

        generator = new Generator(expectedConstant, constant(constType).andThen(expressionType::cast), binary, randomValue);
        for (final Op<E> var : vars) {
            generator.variable(var);
        }
        generator.binary("+",  200, add, Add.class);
        generator.binary("-", -200, sub, Subtract.class);
        generator.binary("*",  301, mul, Multiply.class);
        generator.binary("/", -300, div, Divide.class);
    }

    @Override
    public String toString() {
        return type.getSimpleName();
    }

    @Override
    protected void test() {
        counter.scope("Basic tests", () -> basic.forEach(Test::test));
        counter.scope("Advanced tests", () -> advanced.forEach(Test::test));
        counter.scope("Random tests", generator::testRandom);
    }

    @SuppressWarnings("MethodOnlyUsedFromInnerClass")
    private void checkEqualsAndToString(final String full, final String mini, final ToMiniString expression, final ToMiniString copy) {
        checkToString("toString", full, expression.toString());
        if (mode > 0) {
            checkToString("toMiniString", mini, expression.toMiniString());
        }

        counter.test(() -> {
            assertTrue("Equals to this", expression.equals(expression));
            assertTrue("Equals to copy", expression.equals(copy));
            assertTrue("Equals to null", !expression.equals(null));
            assertTrue("Copy equals to null", !copy.equals(null));
        });

        final String expressionToString = Objects.requireNonNull(expression.toString());
        for (final Pair<ToMiniString, String> pair : prev) {
            counter.test(() -> {
                final ToMiniString prev = pair.first;
                final String prevToString = pair.second;
                final boolean equals = prevToString.equals(expressionToString);
                assertTrue("Equals to " + prevToString, prev.equals(expression) == equals);
                assertTrue("Equals to " + prevToString, expression.equals(prev) == equals);
                assertTrue("Inconsistent hashCode for " + prev + " and " + expression, (prev.hashCode() == expression.hashCode()) == equals);
            });
        }
    }

    private void checkToString(final String method, final String expected, final String actual) {
        counter.test(() -> assertTrue(String.format("Invalid %s\n     expected: %s\n       actual: %s", method, expected, actual), expected.equals(actual)));
    }

    @SuppressWarnings("MethodOnlyUsedFromInnerClass")
    private void check(final String full, final E expected, final E actual, final V v) {
        counter.test(() -> assertEquals(String.format("f(%s)\n%s", v, full), evaluate(expected, v), evaluate(actual, v)));
    }

    private Object evaluate(final E expression, final V value) {
        try {
            return evaluate.apply(expression, value);
        } catch (final Exception e) {
            return e.getClass().getName();
        }
    }

    protected ExpressionTester<E, V, C> basic(final String full, final String mini, final E expected, final Object actual) {
        return basic(new Test(full, mini, expected, type.cast(actual)));
    }

    private ExpressionTester<E, V, C> basic(final Test test) {
        basic.add(test);
        return this;
    }

    public ExpressionTester<E, V, C> basic(final Node<C> node, final Object expression) {
        return basic(generator.test(node, type.cast(expression)));
    }

    protected ExpressionTester<E, V, C> advanced(final String full, final String mini, final E expected, final Object actual) {
        advanced.add(new Test(full, mini, expected, type.cast(actual)));
        return this;
    }

    protected static <E> Op<E> variable(final String name, final E expected) {
        return Op.of(name, expected);
    }

    public static Function<Object, Const> constant(final Class<?> type) {
        try {
            final MethodHandle constructor = MethodHandles.publicLookup().findConstructor(Const.class, MethodType.methodType(void.class, type));
            return t -> {
                try {
                    return (Const) constructor.invoke(t);
                } catch (final Throwable e) {
                    throw Asserts.error("Cannot create new Const(%s): %s", t, e);
                }
            };
        } catch (final IllegalAccessException | NoSuchMethodException e) {
            return t -> {
                throw Asserts.error("Cannot find constructor Const(%s): %s", type, e);
            };
        }
    }

    @FunctionalInterface
    public interface Binary<C, E> {
        E apply(BinaryOperator<C> op, E a, E b);
    }

    private final class Test {
        private final String full;
        private final String mini;
        private final E expected;
        private final E actual;

        private Test(final String full, final String mini, final E expected, final E actual) {
            this.full = full;
            this.mini = mini;
            this.expected = expected;
            this.actual = actual;
        }

        private void test() {
            counter.scope(mini, () -> {
                for (final V value : values) {
                    check(mini, expected, actual, value);
                }
                checkEqualsAndToString(full, mini, actual, actual);
                prev.add(Pair.of(actual, full));
            });
        }
    }

    private final class Generator {
        private final expression.common.Generator<C> generator;
        private final FullRenderer<C> full = new FullRenderer<>();
        private final MiniRenderer<C> mini = new MiniRenderer<>();
        private final Renderer<C, E> expected;
        private final Renderer<C, E> actual;
        private final Renderer<C, E> copy;
        private final Binary<C, E> binary;

        private Generator(
                final Function<C, E> expectedConstant,
                final Function<? super C, E> actualConstant,
                final Binary<C, E> binary,
                final Function<ExtendedRandom, C> randomValue
        ) {
            generator = new expression.common.Generator<>(random, () -> randomValue.apply(random));
            expected = new Renderer<>(expectedConstant);
            actual = new Renderer<>(actualConstant);
            copy = new Renderer<>(actualConstant);

            this.binary = binary;
        }

        @SuppressWarnings("unchecked")
        private void variable(final Op<E> variable) {
            final String name = variable.name;
            generator.add(name, 0);
            full.nullary(name);
            mini.nullary(name);

            expected.nullary(name, variable.value);
            actual.nullary(name, (E) new Variable(name));
            copy.nullary(name, (E) new Variable(name));
        }

        private void binary(final String name, final int priority, final BinaryOperator<C> op, final Class<?> type) {
            generator.add(name, 2);
            full.binary(name);
            mini.binary(name,  priority);

            expected.binary(name, (a, b) -> binary.apply(op, a, b));

            @SuppressWarnings("unchecked") final Constructor<? extends E> constructor = (Constructor<? extends E>) Arrays.stream(type.getConstructors())
                    .filter(cons -> Modifier.isPublic(cons.getModifiers()))
                    .filter(cons -> cons.getParameterCount() == 2)
                    .findFirst()
                    .orElseGet(() -> counter.fail("%s(..., ...) constructor not found", type.getSimpleName()));
            final BinaryOperator<E> actual = (a, b) -> {
                try {
                    return constructor.newInstance(a, b);
                } catch (final Exception e) {
                    return counter.fail(e);
                }
            };
            this.actual.binary(name, actual);
            copy.binary(name, actual);
        }

        private void testRandom() {
            generator.testRandom(1, counter, test -> {
                final String full = this.full.render(test);
                final String mini = this.mini.render(test);
                final E expected = this.expected.render(test);
                final E actual = this.actual.render(test);

                checkEqualsAndToString(full, mini, actual, copy.render(test));
                check(full, expected, actual, randomVars.apply(random));
            });
        }

        public Test test(final Node<C> node, final E expression) {
            return new Test(full.render(node), mini.render(node), expected.render(node), expression);
        }
    }
}
