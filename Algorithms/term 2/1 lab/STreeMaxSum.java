import java.util.Scanner;

public class STreeMaxSum {

    public static class Node {
        Node leftChild;
        Node rightChild;
        int left;
        int right;
        long sum;
        long pref;
        long suff;
        long result;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
            child.sum = a[l];
            child.pref = Math.max(0, a[l]);
            child.suff = Math.max(0, a[l]);
            child.result = Math.max(0, a[l]);
            return child;
        }
        int mid = (l + r) / 2;
        Node leftChild = build(a, l, mid);
        Node rightChild = build(a, mid + 1, r);
        Node root = new Node();
        root.left = leftChild.left;
        root.right = rightChild.right;
        root.sum = leftChild.sum + rightChild.sum;
        root.suff = Math.max(rightChild.sum + leftChild.suff, rightChild.suff);
        root.pref = Math.max(leftChild.sum + rightChild.pref, leftChild.pref);
        long maxSegment = Math.max(leftChild.result, rightChild.result);
        root.result = Math.max(leftChild.suff + rightChild.pref, maxSegment);
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

    public static void set(Node node, int i, int value) {
        if (node.right == node.left && node.left == i) {
            node.sum = value;
            node.pref = Math.max(0, value);
            node.suff = Math.max(0, value);
            node.result = Math.max(0, value);
            return;
        }
        long mid = (node.left + node.right) / 2;
        if (i <= mid) {
            set(node.leftChild, i, value);
        } else {
            set(node.rightChild, i, value);
        }
        node.sum = node.leftChild.sum + node.rightChild.sum;
        node.suff = Math.max(node.rightChild.sum + node.leftChild.suff, node.rightChild.suff);
        node.pref = Math.max(node.leftChild.sum + node.rightChild.pref, node.leftChild.pref);
        long maxSegment = Math.max(node.leftChild.result, node.rightChild.result);
        node.result = Math.max(node.leftChild.suff + node.rightChild.pref, maxSegment);
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

        long[] res = new long[m+1];
        res[0] = root.result;

        for (int i = 1; i < m+1; i++) {
            set(root, in.nextInt(), in.nextInt());
            res[i] = root.result;
        }
        for (int i = 0; i < m+1; i++) {
            System.out.println(res[i]);
        }

    }

}
