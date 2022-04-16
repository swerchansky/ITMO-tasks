package expression;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Add(new Variable("x"), new Variable("X")).equals(null));
    }
}
