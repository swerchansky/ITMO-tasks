import java.util.Scanner;

public class D {

    public static class Node {
        Node leftChild;
        Node rightChild;
        int left;
        int right;
        int sum;
    }

    public static Node build(long[] a, int l, int r) {
        if (l == r) {
            Node child = new Node();
            child.left = l;
            child.right = r;
            child.sum = (int) a[l];
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

    public static int getSum(Node node, int l, int r) {
        if (node.left >= l && node.right <= r) {
            return node.sum;
        } else if (node.left > r || node.right < l) {
            return 0;
        }
        return getSum(node.leftChild, l, r) + getSum(node.rightChild, l, r);
    }

    public static long set(Node node, int i) {
        long calc;
        if (node.right == node.left && node.left == i) {
            int value;
            if (node.sum == 0) {
                value = 1;
            } else {
                value = 0;
            }
            calc = value - node.sum;
            node.sum = value;
            return calc;
        }
        long mid = (node.left + node.right) / 2;
        if (i <= mid) {
            calc = set(node.leftChild, i);
        } else {
            calc = set(node.rightChild, i);
        }
        node.sum += calc;
        return calc;
    }

    public static int find(Node root, int i) {
        if (root.right == root.left) {
            return root.left;
        }
        int left = getSum(root.leftChild, root.leftChild.left, root.leftChild.right);
        int right = getSum(root.rightChild, root.rightChild.left, root.rightChild.right);
        if (left > i) {
             return find(root.leftChild, i);
        } else {
            return find(root.rightChild, i - left);
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
                set(root, in.nextInt());
            } else {
                res[count] = find(root, in.nextInt());
//                System.out.println(res[count]);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i]);
        }

    }

}
