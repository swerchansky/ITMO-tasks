import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
 
public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Scanner string = new Scanner(str);
        int n = string.nextInt();
        int[][] input_list = new int[n][6];
        int[][] tt = new int[n][(int) Math.pow(2, 5)];
        int[] result = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            str = in.nextLine();
            Scanner arg = new Scanner(str);
            int tmp = arg.nextInt();
            if (tmp != 0) {
                input_list[i][0] = tmp;
                for (int j = 0; j < tmp; j++) {
                    int tmp1 = arg.nextInt();
                    input_list[i][j+1] = tmp1;
                }
                result[i] = -1;
                str = in.nextLine();
                arg.close();
                arg = new Scanner(str);
                for (int j = 0; j < Math.pow(2, input_list[i][0]); j++) {
                    tmp = arg.nextInt();
                    tt[i][j] = tmp;
                }
            } else {
                k++;
            }
            arg.close();
        }
 
        int[] result_1 = new int[n];
        for (int i = 0; i < result.length; i++) {
            result_1[i] = result[i];
        }
        int[] pos_k = new int[k];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (result[i] == 0) {
                pos_k[count] = i;
                count++;
            }
        }
        StringBuilder result_str = new StringBuilder();
        for (int i = 0; i < Math.pow(2, k); i++) {
            int[] per = new int[k];
            String num_1 = Integer.toBinaryString(i);
            for (int j = 0; j < num_1.length(); j++) {
                per[per.length-1-j] = Integer.parseInt(String.valueOf(num_1.charAt(num_1.length()-1-j)));
            }
            for (int j = 0; j < k; j++) {
                result_1[pos_k[j]] = per[j];
            }
            for (int j = 0; j < n; j++) {
                if (result[j] != -1) {
                    continue;
                } else {
                    StringBuilder num = new StringBuilder();
                    for (int m = 0; m < input_list[j][0]; m++) {
                        if (input_list[j][0] == 1) {
                            num.append(result_1[input_list[j][m+1]-1]);
                            break;
                        }
                        num.append(result_1[input_list[j][m+1]-1]);
                    }
                    int num_2 = Integer.parseInt(num.toString(), 2);
                    result_1[j] = tt[j][num_2];
                }
            }
            result_str.append(result_1[result_1.length-1]);
        }
        int[] depth = new int[n];
        int count_depth = 0;
        for (int i = 0; i < n; i++) {
            if (result[i] != -1) {
                continue;
            } else {
                count_depth = depth[i];
                for (int j = 1; j < input_list[i][0]+1; j++) {
                    count_depth = Math.max(count_depth, depth[input_list[i][j]-1]);
                }
                depth[i] = count_depth + 1;
            }
        }
        System.out.println(depth[depth.length-1]);
        System.out.println(result_str.toString());
    }
}
