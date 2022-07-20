import java.util.Scanner;

public class E {

    public static class Node {
        Node leftChild;
        Node rightChild;
        long left;
        long right;
        long min;
        int ind;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
            child.min = a[l];
            child.ind = l;
            return child;
        }
        int mid = (l + r) / 2;
        Node leftChild = build(a, l, mid);
        Node rightChild = build(a, mid + 1, r);
        Node root = new Node();
        root.left = leftChild.left;
        root.right = rightChild.right;
        if (leftChild.min > rightChild.min) {
            root.min = leftChild.min;
            root.ind = leftChild.ind;
        } else if (leftChild.min < rightChild.min) {
            root.min = rightChild.min;
            root.ind = rightChild.ind;
        } else {
            root.min = rightChild.min;
            root.ind = leftChild.ind;
        }
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        return root;
    }

    public static long getInd(Node node, int l, int r, int x) {
        if (node.left > r || node.right < l || node.min < x) {
            return Integer.MAX_VALUE;
        } else if (node.left == node.right && node.min >= x) {
            return node.left;
        } else if (node.left == node.right) {
            return Integer.MAX_VALUE;
        }
        return Math.min(getInd(node.leftChild, l, r, x), getInd(node.rightChild, l, r, x));
    }

    public static void set(Node node, int i, int value) {
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

        if (node.leftChild.min > node.rightChild.min) {
            node.min = node.leftChild.min;
            node.ind = node.leftChild.ind;
        } else if (node.leftChild.min < node.rightChild.min) {
            node.min = node.rightChild.min;
            node.ind = node.rightChild.ind;
        } else {
            node.min = node.rightChild.min;
            node.ind = node.leftChild.ind;
        }
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

        long[] res = new long[m];
        int count = 0;

        for (int i = 0; i < m; i++) {
            int mode = in.nextInt();
            if (mode == 1) {
                set(root, in.nextInt(), in.nextInt());
            } else {
                int x = in.nextInt();
                int l = in.nextInt();
                res[count] = getInd(root, l, n - 1, x);
                if (res[count] == Integer.MAX_VALUE) {
                    res[count] = -1;
                }
//                System.out.println(res[count]);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i]);
        }

    }

}

