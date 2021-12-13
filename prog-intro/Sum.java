public class Sum {
    public static void main(String[] args) {
        int num = 0;
        for (String arg : args) {
            String tmp = arg;
            tmp += " ";
            String str = "";
            int count = 0;
            for (int j = 0; j < tmp.length(); j++) {
                if (Character.isWhitespace(tmp.charAt(j))) {
                    try {
                        num += Integer.parseInt(tmp.substring(count, j));
                        count = j + 1;
                    } catch (NumberFormatException e) {
                        count = j + 1;
                    }
                }
            }
        }
        System.out.println(num);
    }
}