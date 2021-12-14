import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            count[i] = scan.nextInt();
        }

        String str = scan.next();

        BigDecimal[] frequency = new BigDecimal[n];
        BigDecimal len = new BigDecimal(str.length());

        int abc = 0;
        for (int i = 0; i < n; i++) {
            abc += count[i];
        }

        for (int i = 0; i < n; i++) {
            frequency[i] = BigDecimal.valueOf(count[i]);
            frequency[i] = frequency[i].divide(BigDecimal.valueOf(abc), 200, RoundingMode.UP);
        }

        BigDecimal[] line = new BigDecimal[n];
        line[0] = frequency[0];

        for (int i = 1; i < n; i++) {
            line[i] = frequency[i].add(line[i-1]);
        }

        BigInteger p = new BigInteger(str, 2);
        BigDecimal tmp = new BigDecimal(p);
//        System.out.println(tmp);
        int q = str.length();
        BigDecimal two = new BigDecimal(2);
        BigDecimal pow = two.pow(q);
        BigDecimal boarder = new BigDecimal(String.valueOf(tmp.divide(pow, 1000, RoundingMode.UP)));
//        System.out.println(q);
//        System.out.println(pow);
//        System.out.println(p);
//        System.out.println(boarder);

//        System.out.println(boarder);
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < abc; i++) {
            int ind = 0;
            for (int j = 0; j < n; j++) {
                if (boarder.compareTo(line[j]) < 0) {
                    res.append((char) (j + 'a'));
                    ind = j;
                    break;
                }
            }
            if (ind > 0) {
                BigDecimal sub = line[ind].subtract(line[ind-1]);
                boarder = boarder.subtract(line[ind-1]).divide(sub, 1000, RoundingMode.UP);
            } else {
                BigDecimal sub = line[ind];
                boarder = boarder.divide(sub, 1000, RoundingMode.UP);
            }
//            System.out.println(res.toString());
//            System.out.println(boarder);
        }
        System.out.println(res.toString());

//        for (int i = 0; i < n; i++) {
//            System.out.println(line[i]);
//        }

    }
}
