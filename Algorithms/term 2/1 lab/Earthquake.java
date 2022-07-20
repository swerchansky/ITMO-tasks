import java.util.Scanner;

public class Earthquake {
    private static Node root;

    public static class Node {
        Node leftChild;
        Node rightChild;
        long left;
        long right;
        long min;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
            child.min = a[l];
            return child;
        }
        int mid = (l + r) / 2;
        Node leftChild = build(a, l, mid);
        Node rightChild = build(a, mid + 1, r);
        Node root = new Node();
        root.left = leftChild.left;
        root.right = rightChild.right;
        if (leftChild.min < rightChild.min) {
            root.min = leftChild.min;
        } else if (leftChild.min > rightChild.min) {
            root.min = rightChild.min;
        } else {
            root.min = rightChild.min;
        }
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        return root;
    }

    public static long getCount(Node node, int l, int r, int p) {
        if (node.left > r || node.right < l || node.min > p) {
            return 0;
        } else if (node.left == node.right) {
            if (node.min <= p) {
                set(root, node.left, Long.MAX_VALUE);
                return 1;
            } else {
                return 0;
            }
        }
        return getCount(node.leftChild, l, r, p) + getCount(node.rightChild, l, r, p);
    }

    public static void set(Node node, long i, long value) {
        if (node.right == node.left && node.left == i) {
            node.min = value;
            return;
        }
        long mid = (node.left + node.right) / 2;
        if (i <= mid) {
            set(node.leftChild, i, value);
        } else {
            set(node.rightChild, i, value);
        }

        if (node.leftChild.min < node.rightChild.min) {
            node.min = node.leftChild.min;
        } else if (node.leftChild.min > node.rightChild.min) {
            node.min = node.rightChild.min;
        } else {
            node.min = node.rightChild.min;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = Long.MAX_VALUE;
        }

        root = build(a, 0, n-1);

        long[] res = new long[m];
        int count = 0;

        for (int i = 0; i < m; i++) {
            int mode = in.nextInt();
            if (mode == 1) {
                set(root, in.nextInt(), in.nextInt());
            } else {
                int l = in.nextInt();
                int r = in.nextInt();
                int p = in.nextInt();
                res[count] = getCount(root, l, r - 1, p);
//                System.out.println(res[count]);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i]);
        }

    }

}

