package expression.exceptions;

public class Main {
    public static void main(String[] args) {
        try {
            ExpressionParser parser = new ExpressionParser();
            System.out.println(parser.parse("-012"));
            System.out.println(parser.parse("(y + z) + (z + y)").evaluate(-1680095569, -1142891575, 1832719083));
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
        }
//        System.out.println("x f");
//        for (int i = 0; i < 11; i++) {
//            try {
//                ExpressionParser parser = new ExpressionParser();
//                System.out.println(i + " " + parser.parse("1000000*x*x*x*x*x/(x-1)").evaluate(i));
//            } catch (ArithmeticException | ParsingException e) {
//                System.out.println(i + " " + e.getMessage());
//            }
//        }
    }
}