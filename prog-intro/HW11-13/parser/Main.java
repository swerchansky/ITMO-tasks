package expression.parser;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        System.out.println(parser.parse("(\n" +
                "\n" +
                "-((             -297546000 * \n" +
                "\n" +
                "-1000484351) \n" +
                "                )\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "                        -\n" +
                "\n" +
                "\n" +
                "                          \n" +
                "(\n" +
                "      (x\n" +
                "\n" +
                "+\n" +
                "\n" +
                "-883980809)\n" +
                " *\n" +
                "                \n" +
                "(x \n" +
                "                        \n" +
                "\n" +
                "        +\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                " -542129744))\n" +
                "\n" +
                "             )\n"));
    }
}

// 1 (1) -(1) (((1))) (-(1))
