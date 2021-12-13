import java.io.IOException;

public class Reverse {
    public static int[] increaseSize(int[] arr) {
        int[] temp = new int[arr.length * 2];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        return temp;
    }

    public static void main(String[] args) {
        try {
            FastScanner stringScanner = new FastScanner(System.in);
            int[] arrNums = new int[5];
            int[] arrLines = new int[5];
            int posArrNums = 0;
            int countLines = 0;
            while (stringScanner.hasNextLine()) {
                if (posArrNums >= arrNums.length) {
                    arrNums = increaseSize(arrNums);
                }
                if (countLines >= arrLines.length) {
                    arrLines = increaseSize(arrLines);
                }
                String str = stringScanner.nextLine();
                FastScanner tmp = new FastScanner(str);
                while (tmp.hasNext()) {
                    if (posArrNums >= arrNums.length) {
                        arrNums = increaseSize(arrNums);
                    }
                    String number = tmp.next();
                    if (!number.isEmpty()) {
                        arrNums[posArrNums] = Integer.parseInt(number);
                        arrLines[countLines]++;
                        posArrNums++;
                    }
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
                    System.out.print("\n ");
                }
            }
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}