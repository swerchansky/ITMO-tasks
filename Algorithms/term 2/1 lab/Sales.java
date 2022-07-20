import java.util.Scanner;

public class Sales {

    public static void swap(Node[] a, int x, int y) {
        Node tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    public static void qSortNums(Node[] a, int x, int y) {
        if (x >= y) {
            return;
        }
        int pivot = a[x + (y - x) / 2].x;
        int less = x;
        int greater = y;
        while (x <= y) {
            while (x < a.length && a[x].x < pivot) {
                x++;
            }
            if (x == a.length) {
                swap(a, x + (y - x) / 2, x - 1);
                qSortNums(a, 0, a.length - 1);
            }
            while (a[y].x > pivot) {
                y--;
            }
            if (x <= y) {
                swap(a, x, y);
                x++;
                y--;
            }
        }
        if (less < y) {
            qSortNums(a, less, y);
        }
        if (greater > x) {
            qSortNums(a, x, greater);
        }
    }

    public static class Node {
        int x;
        int type;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();


        for (int i = 0; i < n; i++) {

        }

    }

}
