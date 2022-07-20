package search;

public class BinarySearch {
    // Pred: 1 <= i < j < args.length: args[i] >= args[j] && args[0] == x
    // Post: R = r: array[r] == x && 0 <= r < array.length
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] array = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            array[i - 1] = Integer.parseInt(args[i]);
        }
//        System.out.println(iterativeSearch(array, x));
        System.out.println(recursiveSearch(array, x, -1, array.length));
    }

    // Pred(pr): 1 <= i < j < array.length: array[i] >= array[j] && args[0] == x
    // Post: R = r: array[r] == x && 0 <= r < array.length
    private static int iterativeSearch(int[] array, int x) {
        // Pred: (pr)
        int l = -1;
        // Post: l = -1 && (pr)
        // Pred: l = -1 && (pr)
        int r = array.length;
        // Post: r = array.length && l = -1 && (pr)
        // Invariant: array[l] > x && array[r] <= x && l <= l' && r' <= r && r >= 0 && r' < array.length
        //  && l >= -1 && l < array.length && r - l > 1
        while (r - l > 1) {
            // Pred: l' < (r' + l') / 2 < r' && Invariant
            int mid = (r + l) / 2;
            // Post l' < mid < r' && Invariant
            if (array[mid] > x) {
                // Pred: mid = (r' + l') / 2 && array[mid] > x && Invariant
                l = mid;
                // Post: l = (r' + l') / 2 && array[l] > x && Invariant
            } else {
                // Pred: mid = (r' + l') / 2 && array[mid] <= x && Invariant
                r = mid;
                // Post: r = (r' + l') / 2 && array[r] <= x && Invariant
            }
        }
        // r - l <= 1 && 0 <= r < array.length: array[r] == x
        return r;
        // R = r
    }

    // Pred(pr): 1 <= i < j < array.length: array[i] >= array[j] && args[0] == x && -1 <= l < r < array.length
    // Post: R = r: array[r] == x && 0 <= r < array.length
    private static int recursiveSearch(int[] array, int x, int l, int r) {
        if (r - l <= 1) {
            // 0 <= r <= array.length && r - l <= 1 && pr
            return r;
            // R = r: array[r] == x && 0 <= r < array.length
        }
        // Pred: l < (r + l) / 2 < r && pr
        int mid = (r + l) / 2;
        // Post: l < mid < r && pr
        if (array[mid] > x) {
            // Pred: l < mid < r && array[mid] > x && pr
            return recursiveSearch(array, x, mid, r);
            // Post: R = recursiveSearch(array, x, (r + l) / 2, r)
        } else {
            // Pred: l < mid < r && array[mid] <= x && pr
            return recursiveSearch(array, x, l, mid);
            // Post: R = recursiveSearch(array, x, l, (r + l) / 2)
        }
    }

}
