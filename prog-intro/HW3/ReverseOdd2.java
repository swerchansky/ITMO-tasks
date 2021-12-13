import java.io.IOException;
import java.util.Scanner;

public class ReverseOdd2 {
    public static int[] increaseSize(int[] arr){
        int[] temp = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++){
            temp[i] = arr[i];
        }
        return temp;
    }
    public static void main(String[] args) throws IOException {
        FastScanner scan = new FastScanner(System.in);
        int[] arrNums = new int[5];
        int[] arrLines = new int[5];
        int posArrNums = 0;
        int countLines = 0;
        while (scan.hasNextLine()) {
            int countNums = 0;
            if (posArrNums >= arrNums.length) {
                arrNums = increaseSize(arrNums);
            }
            if (countLines >= arrLines.length) {
                arrLines = increaseSize(arrLines);
            }
            Scanner tmp = new Scanner(scan.nextLine());
            while (tmp.hasNextInt()) {
                if (posArrNums >= arrNums.length) {
                    arrNums = increaseSize(arrNums);
                }
                int number = tmp.nextInt();
                if ((countLines + countNums) % 2 != 0) {
                    arrNums[posArrNums] = number;
                    arrLines[countLines]++;
                    posArrNums++;
                }
                countNums++;
            }
            tmp.close();
            countLines++;
        }
        for (int i = countLines - 1; i >= 0 ; i--) {
            for (int j = 0; j < arrLines[i]; j++) {
                System.out.print(arrNums[posArrNums - 1] + " ");
                posArrNums--;
            }
            if (i != 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
