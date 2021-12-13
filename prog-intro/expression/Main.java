package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Subtract(new Variable("x"), new Add(new Multiply(new Const(1), new Const(2)), new Variable("y"))).evaluate(3, 2, 4));
    }
}
