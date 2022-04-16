package expression;

public class MaxBin extends UnaryOperations {

    public MaxBin(MiniExpression x) {
        super(x);
    }

    @Override
    protected int count(int x) {
        int count = 0;
        String tmp = Integer.toBinaryString(x);
        if (tmp.equals("0")) {
            return 32;
        }
        return 32 - tmp.length();
    }

    @Override
    protected String getType() {
        return "l0";
    }

}