package expression.generic;

import expression.GExpression;
import expression.exceptions.ArithmeticException;
import expression.exceptions.ParsingException;
import expression.generic.arithmetics.*;
import expression.parser.ExpressionParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private final Map<String, ArithmeticTabulator<?>> tabulators = Map.of(
            "i", new IntegerTabulator(),
            "d", new DoubleTabulator(),
            "bi", new BigIntegerTabulator()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2)
            throws ParsingException {
        return parseTable(tabulators.get(mode), expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] parseTable(ArithmeticTabulator<T> tabulator, String expression, int x1, int x2, int y1,
                                        int y2, int z1, int z2) throws ParsingException {
        GExpression<T> exp = new ExpressionParser<T>().parse(expression);
        Object[][][] result = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
        for (int i = 0; i < x2 - x1 + 1; i++) {
            for (int j = 0; j < y2 - y1 + 1; j++) {
                for (int k = 0; k < z2 - z1 + 1; k++) {
                    try {
                        result[i][j][k] = exp.evaluate(tabulator.value(x1 + i),
                                tabulator.value(y1 + j), tabulator.value(z1 + k), tabulator);
                    } catch (ArithmeticException ignored) {

                    }
                }
            }
        }
        return result;
    }
}
