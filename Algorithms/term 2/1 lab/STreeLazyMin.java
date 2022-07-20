import java.util.Scanner;

public class STreeLazyMin {

    public static class NodeTree {
        NodeTree leftChild;
        NodeTree rightChild;
        boolean fl;
        long left;
        long right;
        long min;
    }

    public static NodeTree build(long[] a, int l, int r) {
        if (l == r) {
            NodeTree child = new NodeTree();
            child.left = l;
            child.right = r;
            child.min = a[l];
            child.fl = false;
            return child;
        }
        int mid = (l + r) / 2;
        NodeTree leftChild = build(a, l, mid);
        NodeTree rightChild = build(a, mid + 1, r);
        NodeTree root = new NodeTree();
        root.left = leftChild.left;
        root.right = rightChild.right;
        root.min = Math.min(leftChild.min, rightChild.min);
        root.leftChild = leftChild;
        root.rightChild = rightChild;
        return root;
    }

    public static void push(NodeTree node) {
        if (node.fl) {
            node.leftChild.min = node.min;
            node.rightChild.min = node.min;
            node.leftChild.fl = true;
            node.rightChild.fl = true;
            node.fl = false;
        }
    }

    public static long getMin(NodeTree node, int l, int r) {
        if (node.left != node.right) {
            push(node);
        }
        if (node.left >= l && node.right <= r) {
            return node.min;
        } else if (node.left > r || node.right < l) {
            return Integer.MAX_VALUE;
        }
        return Math.min(getMin(node.leftChild, l, r), getMin(node.rightChild, l, r));
    }

    public static long set(NodeTree node, int l, int r, int value) {
        if (node.left != node.right) {
            push(node);
        }
        if (node.left >= l && node.right <= r) {
            node.min = value;
            node.fl = true;
            return value;
        } else if (node.left > r || node.right < l) {
            return node.min;
        }
        node.min = Math.min(set(node.leftChild, l, r, value), set(node.rightChild, l, r, value));
        return node.min;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        long[] a = new long[n];

        NodeTree root = build(a, 0, n - 1);

        long[] res = new long[m];
        int count = 0;

        for (int i = 0; i < m; i++) {
            int mode = in.nextInt();
            if (mode == 1) {
                set(root, in.nextInt(), in.nextInt() - 1, in.nextInt());
            } else {
                res[count] = getMin(root, in.nextInt(), in.nextInt() - 1);
//                System.out.println(res[count]);
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.println(res[i]);
        }

    }

}
