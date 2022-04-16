package expression.exceptions;

import expression.MiniExpression;

public class CheckedMinBin extends UnaryOperations {

    public CheckedMinBin(MiniExpression x) {
        super(x);
    }

    @Override
    protected int count(int x) {
        int count = 0;
        if (x == 0) {
            return 32;
        }
        while ((x & 1) == 0) {
            count++;
            x >>= 1;
        }
        return count;
    }

    @Override
    protected String getType() {
        return "t0";
    }

}
