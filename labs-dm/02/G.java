import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class G {
    public static void main(String[] args) {
        Map<Character, Integer> d = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        String str = scan.next();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (d.containsKey(ch)) {
                int num = d.get(ch);
                d.replace(ch, num + 1);
            }
            else {
                d.put(ch, 1);
            }
        }
        int[] count = new int[n];
        int ind = 0;
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            count[(int) num - 'a'] = d.get(num);
        }

        BigDecimal[] frequency = new BigDecimal[n];
        BigDecimal len = new BigDecimal(str.length());

        for (int i = 0; i < n; i++) {
            frequency[i] = BigDecimal.valueOf(count[i]);
            frequency[i] = frequency[i].divide(len, 26, RoundingMode.DOWN);
        }

        BigDecimal[] line = new BigDecimal[n];
        line[0] = frequency[0];

        for (int i = 1; i < n; i++) {
            line[i] = frequency[i].add(line[i-1]);
        }

        BigDecimal l_cur = BigDecimal.valueOf(0);
        BigDecimal r_cur = BigDecimal.valueOf(1);
        BigDecimal l = l_cur;
        BigDecimal r = r_cur;
        BigDecimal tmp = new BigDecimal(0);
        BigDecimal tmp1 = new BigDecimal(0);

        for (int i = 0; i < str.length(); i++) {
            ind = str.charAt(i) - 'a';
            if (ind > 0) {
                tmp = l_cur;
                l_cur = r_cur.subtract(l_cur).multiply(line[ind-1]).add(l);
                r_cur = r_cur.subtract(tmp).multiply(line[ind]).add(l);
            } else {
                r_cur = r_cur.subtract(l_cur).multiply(line[ind]).add(l);
            }
            l = l_cur;
            r = r_cur;
        }

        System.out.println(l_cur);
        System.out.println(r_cur);
        BigDecimal q = new BigDecimal(1);
        BigDecimal divisor = new BigDecimal(2);
        BigDecimal p = new BigDecimal(0);
        BigDecimal res = new BigDecimal(0);

        while (p.compareTo(divisor) > 0 || res.compareTo(l_cur) < 0 || res.compareTo(r_cur) >= 0) {
            p = BigDecimal.valueOf(1);
            p = p.multiply(l_cur).multiply(divisor).setScale(0, RoundingMode.DOWN);

            res = p.divide(divisor);
            p = p.add(BigDecimal.valueOf(1));

            while (p.compareTo(divisor) <= 0 && res.compareTo(l_cur) < 0) {
                res = p.divide(divisor);
                p = p.add(BigDecimal.valueOf(1));
            }
            q = q.add(BigDecimal.valueOf(1));
            divisor = divisor.multiply(BigDecimal.valueOf(2));
        }

        q = q.subtract(BigDecimal.valueOf(1));
        p = p.subtract(BigDecimal.valueOf(1));
        BigInteger result = p.toBigInteger();

        System.out.println(p);

        System.out.println(l_cur);
        System.out.println(r_cur);

        System.out.println(n);

        for (int i = 0; i < n; i++) {
            System.out.print(count[i] + " ");
        }

        System.out.println();

        String res1 = result.toString(2);

        for (int i = 0; i < q.intValue() - res1.length(); i++) {
            System.out.print('0');
        }
        if (res1.charAt(0) != '-') {
            System.out.print(res1);
        } else {
            System.out.print('0');
        }
    }
}
