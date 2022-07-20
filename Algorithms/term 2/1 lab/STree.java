import java.util.Scanner;

public class STree {

    public static class Node {
        Node leftChild;
        Node rightChild;
        long left;
        long right;
        long sum;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
            child.sum = a[l];
            return child;
        }
        int mid = (l + r) / 2;
        Node leftChild = build(a, l, mid);
        Node rightChild = build(a, mid + 1, r);
        Node root = new Node();
        root.left = leftChild.left;
        root.right = rightChild.right;
        root.sum = leftChild.sum + rightChild.sum;
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        return root;
    }

    public static long getSum(Node node, int l, int r) {
        if (node.left >= l && node.right <= r) {
            return node.sum;
        } else if (node.left > r || node.right < l) {
            return 0;
        }
        return getSum(node.leftChild, l, r) + getSum(node.rightChild, l, r);
    }

    public static long set(Node node, int i, int value) {
        long calc;
        if (node.right == node.left && node.left == i) {
            calc = value - node.sum;
            node.sum = value;
            return calc;
        }
        long mid = (node.left + node.right) / 2;
        if (i <= mid) {
            calc = set(node.leftChild, i, value);
        } else {
            calc = set(node.rightChild, i, value);
        }
        node.sum += calc;
        return calc;
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
                res[count] = getSum(root, in.nextInt(), in.nextInt() - 1);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i]);
        }

    }

}
