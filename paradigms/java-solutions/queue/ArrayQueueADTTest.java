package queue;

import static queue.ArrayQueueADT.*;

public class ArrayQueueADTTest {
    private static final ArrayQueueADT queue1 = new ArrayQueueADT();
    private static final ArrayQueueADT queue2 = new ArrayQueueADT();

    public static void fill1(int n) {
        for (int i = 0; i < n; i++) {
            enqueue(queue1, "1_" + i);
        }
    }

    public static void fill2(int n) {
        for (int i = 0; i < n; i++) {
            enqueue(queue2, "2_" + i);
        }
    }

    public static void dump1(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(dequeue(queue1));
        }
    }

    public static void dump2(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(dequeue(queue2));
        }
    }

    public static void clear(ArrayQueueADT queue) {
        ArrayQueueADT.clear(queue);
        System.out.println(isEmpty(queue));
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
        System.out.println(element(queue1));
        System.out.println("fill/dump & element test queue2:");
        fill2(10);
        dump2(5);
        System.out.println(element(queue2));
        System.out.println("different test:");
        clear(queue1);
        clear(queue2);
        fill1(5);
        fill2(7);
        count(queue1, "1_2");
        count(queue1, "1_5");
        count(queue1, "1_100");
        System.out.println(size(queue1));
        System.out.println(size(queue2));

    }

}
