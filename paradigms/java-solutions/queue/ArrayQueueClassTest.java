package queue;


public class ArrayQueueClassTest {
    private static final ArrayQueue queue1 = new ArrayQueue();
    private static final ArrayQueue queue2 = new ArrayQueue();

    public static void fill1(int n) {
        for (int i = 0; i < n; i++) {
            queue1.enqueue("1_" + i);
        }
    }

    public static void fill2(int n) {
        for (int i = 0; i < n; i++) {
            queue2.enqueue("2_" + i);
        }
    }

    public static void dump1(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(queue1.dequeue() + " " + queue1.size());
        }
    }

    public static void dump2(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(queue2.dequeue() + " " + queue2.size());
        }
    }

    public static void clear(ArrayQueue queue) {
        queue.clear();
        System.out.println(queue.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println("fill/dump test queue1:");
        fill1(5);
        dump1(5);
        System.out.println("fill/dump test queue2:");
        fill2(5);
        dump2(5);
        System.out.println("fill/clear test queue1:");
        fill1(10);
        clear(queue1);
        System.out.println("fill/clear test queue2:");
        fill2(10);
        clear(queue2);
        System.out.println("fill/dump & element test queue1:");
        fill1(10);
        dump1(5);
        System.out.println(queue1.element());
        System.out.println("fill/dump & element test queue2:");
        fill2(10);
        dump2(5);
        System.out.println(queue2.element());
        System.out.println("different test:");
        clear(queue1);
        clear(queue2);
        fill1(5);
        fill2(7);
        System.out.println(queue1.size());
        System.out.println(queue2.size());
    }

}
