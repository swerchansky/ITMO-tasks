
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Scanline {
    private static Map<Integer, Integer> map = new HashMap<>();

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

    public static void qSortTypes(Node[] a, int x, int y) {
        if (x >= y) {
            return;
        }
        int pivot = a[x + (y - x) / 2].type;
        int less = x;
        int greater = y;
        while (x <= y) {
            while (x < a.length && a[x].type < pivot) {
                x++;
            }
            if (x == a.length) {
                swap(a, x + (y - x) / 2, x - 1);
                qSortTypes(a, 0, a.length - 1);
            }
            while (a[y].type > pivot) {
                y--;
            }
            if (x <= y) {
                swap(a, x, y);
                x++;
                y--;
            }
        }
        if (less < y) {
            qSortTypes(a, less, y);
        }
        if (greater > x) {
            qSortTypes(a, x, greater);
        }
    }

    public static class Node {
        int x;
        int type;
        int ind;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] segments = new Node[n * 2 + m];
        for (int i = 0; i < n * 2; i += 2) {
            int num;
            segments[i] = new Node();
            segments[i].x = in.nextInt();
            num = map.getOrDefault(segments[i].x, 0);
            map.put(segments[i].x, num + 1);
            segments[i].type = 1;
            segments[i + 1] = new Node();
            segments[i + 1].x = in.nextInt();
            num = map.getOrDefault(segments[i+1].x, 0);
            map.put(segments[i + 1].x, num + 1);
            segments[i + 1].type = -1;
        }

        int num = 0;
        for (int i = n * 2; i < n * 2 + m; i++) {
            segments[i] = new Node();
            segments[i].x = in.nextInt();
            int b = map.getOrDefault(segments[i].x, 0);
            map.put(segments[i].x, b + 1);
            segments[i].type = 0;
            segments[i].ind = num++;
        }


        qSortNums(segments, 0, n * 2 + m - 1);

//        for (int i = 0; i < n * 2 + m; i++) {
//            System.out.print(segments[i].x + " ");
//        }

//        System.out.println();

        int count = 0;
        int[] res = new int[m];
        int j = 0;

        int i = 0;
        while (i < segments.length) {
            num = map.get(segments[i].x);
            if (num > 1) {
                Node[] tmp = new Node[num];
                for (int k = i; k < i + num; k++) {
                    tmp[k] = segments[k];
                }
                qSortTypes(tmp, 0, num-1);
                for (int l = 0; l < tmp.length / 2; l++) {
                    Node temp = tmp[l];
                    tmp[l] = tmp[tmp.length - 1 - l];
                    tmp[tmp.length - 1 - l] = temp;
                }
                for (int k = 0; k < num; k++) {
                    if (tmp[k].type == 1) {
                        count++;
                    } else if (tmp[k].type == -1) {
                        count--;
                    } else {
                        res[tmp[k].ind] = count;
                    }
                }
                i += num;
            }
            if (segments[i].type == 1) {
                count++;
            } else if (segments[i].type == -1) {
                count--;
            } else {
                res[segments[i].ind] = count;
            }
            i++;
        }

        for (i = 0; i < m; i++) {
            System.out.print(res[i] + " ");
        }

    }

}
