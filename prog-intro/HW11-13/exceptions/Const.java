package expression.exceptions;

import expression.MiniExpression;

import java.util.Objects;

public class Const implements MiniExpression {
    private final int con;

    public Const(int con) {
        this.con = con;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return con;
    }

    @Override
    public int evaluate(int x) {
        return con;
    }

    public String toString() {
        return String.valueOf(con);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Const newOperations) {
            return Objects.equals(con, newOperations.con);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Integer.hashCode(con);
    }

}
