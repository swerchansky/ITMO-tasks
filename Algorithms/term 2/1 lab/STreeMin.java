import java.util.Scanner;

public class STreeMin {

    public static class Node {
        Node leftChild;
        Node rightChild;
        long left;
        long right;
//        long min;
//        long count;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
//            child.min = a[l];
//            child.count = 1;
            return child;
        }
        int mid = (l + r) / 2;
        Node leftChild = build(a, l, mid);
        Node rightChild = build(a, mid + 1, r);
        Node root = new Node();
        root.left = leftChild.left;
        root.right = rightChild.right;
//        if (leftChild.min < rightChild.min) {
//            root.min = leftChild.min;
//            root.count = leftChild.count;
//        } else if (leftChild.min > rightChild.min) {
//            root.min = rightChild.min;
//            root.count = rightChild.count;
//        } else {
//            root.min = rightChild.min;
//            root.count = rightChild.count + leftChild.count;
//        }
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        return root;
    }

    public static long getCount(Node node, int l, int r, int min) {
//        if (node.left >= l && node.right <= r) {
//            if (node.min == min) {
//                return node.count;
//            }
//            return 0;
//        } else if (node.left > r || node.right < l) {
//            return 0;
//        }

        return getCount(node.leftChild, l, r, min) + getCount(node.rightChild, l, r, min);
    }

    public static long getMin(Node node, int l, int r) {
//        if (node.left >= l && node.right <= r) {
//            return node.min;
//        } else if (node.left > r || node.right < l) {
//            return Integer.MAX_VALUE;
//        }
        return Math.min(getMin(node.leftChild, l, r), getMin(node.rightChild, l, r));
    }

    public static void set(Node node, int i, int value) {
//        if (node.right == node.left && node.left == i) {
//            node.min = value;
//            return;
//        }
        long mid = (node.left + node.right) / 2;
        if (i <= mid) {
            set(node.leftChild, i, value);
        } else {
            set(node.rightChild, i, value);
        }

//        if (node.leftChild.min < node.rightChild.min) {
//            node.min = node.leftChild.min;
//            node.count = node.leftChild.count;
//        } else if (node.leftChild.min > node.rightChild.min) {
//            node.min = node.rightChild.min;
//            node.count = node.rightChild.count;
//        } else {
//            node.min = node.rightChild.min;
//            node.count = node.rightChild.count + node.leftChild.count;
//        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        Node root = build(a, 0, n-1);

        long[][] res = new long[m][2];
        int count = 0;

        for (int i = 0; i < m; i++) {
            int mode = in.nextInt();
            if (mode == 1) {
                set(root, in.nextInt(), in.nextInt());
            } else {
                int l = in.nextInt();
                int r = in.nextInt();
                res[count][0] = getMin(root, l, r - 1);
                res[count][1] = getCount(root, l, r - 1, (int) res[count][0]);
//                System.out.println(res[count][0] + " " + res[count][1]);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i][0] + " " + res[i][1]);
        }

    }

}

