import java.util.Scanner;

public class scanline2 {

    public static void swap(long[] a, int x, int y) {
        long tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void qSort(long[] a, int x, int y) {
        if (x >= y) {
            return;
        }
        long pivot = a[x + (y - x) / 2];
        int less = x;
        int greater = y;
        while (x <= y) {
            while (x < a.length && a[x] < pivot) {
                x++;
            }
            if (x == a.length) {
                swap(a, x + (y - x) / 2, x - 1);
                qSort(a, 0, a.length - 1);
            }
            while (a[y] > pivot) {
                y--;
            }
            if (x <= y) {
                swap(a, x, y);
                x++;
                y--;
            }
        }
        if (less < y) {
            qSort(a, less, y);
        }
        if (greater > x) {
            qSort(a, x, greater);
        }
    }

    private static long binSearchRight(long[] a, long key) {
        long l = -1;
        long r = a.length;
        while (l < r - 1){
            int m = (int) ((l + r) / 2);
            if (a[m] <= key) {
                l = m;
            }
            else{
                r = m;
            }
        }
        return r;
    }

    private static long binSearchLeft(long[] a, long key) {
        long l = -1;
        long r = a.length;
        while (l < r - 1){
            int m = (int) ((l + r) / 2);
            if (a[m] < key) {
                l = m;
            }
            else{
                r = m;
            }
        }
        return r;
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] left = new long[n];
        long[] right = new long[n];

        for (int i = 0; i < n; i++) {
            long l = in.nextLong();
            long r = in.nextLong();
            left[i] = Math.min(l, r);
            right[i] = Math.max(l, r);
        }

        qSort(left, 0, n-1);
        qSort(right, 0, n-1);

        long[] res = new long[m];

        for (int i = 0; i < m; i++) {
            res[i] = in.nextLong();
        }

        for (int i = 0; i < m; i++) {
            long dot = res[i];
            res[i] = binSearchRight(left, dot) + 1;
            res[i] -= binSearchLeft(right, dot) + 1;
        }

        for (int i = 0; i < m; i++) {
            System.out.print(res[i] + " ");
        }

    }

}
