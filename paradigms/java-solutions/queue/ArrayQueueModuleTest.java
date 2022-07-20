package queue;

public class ArrayQueueModuleTest {

    public static void fill(int n) {
        for (int i = 0; i < n; i++) {
            ArrayQueueModule.enqueue(i);
        }
    }

    public static void dump(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(ArrayQueueModule.dequeue() + " " + ArrayQueueModule.size());
        }
    }

    public static void clear() {
        ArrayQueueModule.clear();
        System.out.println(ArrayQueueModule.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println("fill/dump test:");
        fill(5);
        dump(5);
        System.out.println("fill/clear test:");
        fill(10);
        clear();
        System.out.println("fill/dump & element test:");
        fill(10);
        dump(5);
        ArrayQueueModule.count("1_5");
        ArrayQueueModule.count("1_2");
        ArrayQueueModule.count("1_100");
        System.out.println(ArrayQueueModule.element());
        System.out.println(ArrayQueueModule.element());
    }

}
