package queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Model: a[1]...a[n]
// Invariant: n >= 0 && for i = 1...n: a[i] != null
public class ArrayQueueADT {
    private Object[] elements = new Object[3];
    private int start, end = 0;
    private Map<Object, Integer> counts = new HashMap<>();

    // Pred: n == elements.length
    // Post: elements.length' = elements.length * 2 && start' = 0 && end' = elements.length && n' = n &&
    // for i = 0...n: elements[i]' = elements[(i+start) % elements.length] &&
    // for i = n+1...elements.length': elements[i] = null
    private static void increaseCapacity(final ArrayQueueADT queue) {
        if (queue.start == queue.end && queue.elements[queue.start] != null) {
            Object[] tmp = new Object[queue.elements.length * 2];
            System.arraycopy(queue.elements, queue.start, tmp, 0, queue.elements.length - queue.start);
            System.arraycopy(queue.elements, 0, tmp, queue.elements.length - queue.start, queue.end);
            queue.end = queue.elements.length;
            queue.elements = tmp;
            queue.start = 0;
        }
    }

    // Pred: True
    // Post: R = counts.getOrDefault(element, 0)
    public static int count(final ArrayQueueADT queue, final Object element) {
        return queue.counts.getOrDefault(element, 0);
    }

    // Pred: element != null
    // Post: n' = n + 1 && elements[end] = element && end' = (end + 1) % elements.length &&
    // counts.get(element)' = counts.get(element) + 1
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        Objects.requireNonNull(element);
        increaseCapacity(queue);
        queue.elements[queue.end] = element;
        queue.end = (queue.end + 1) % queue.elements.length;
        int num = queue.counts.getOrDefault(element, 0);
        queue.counts.put(element, num + 1);
    }

    // Pred: !isEmpty()
    // Post: R = elements[start]
    public static Object element(final ArrayQueueADT queue) {
        assert !isEmpty(queue);
        return queue.elements[queue.start];
    }

    // Pred: !isEmpty()
    // Post: R = elements[start] && start' = (start + 1) % elements.length && elements[start] = null &&
    // counts.get(element)' = counts.get(element) - 1
    public static Object dequeue(final ArrayQueueADT queue) {
        assert !isEmpty(queue);
        Object value = element(queue);
        queue.elements[queue.start] = null;
        queue.start = (queue.start + 1) % queue.elements.length;
        int num = queue.counts.getOrDefault(value, 0);
        queue.counts.put(value, num - 1);
        return value;
    }

    // Pred: true
    // Post: R = n
    public static int size(final ArrayQueueADT queue) {
        if ((queue.start > queue.end) || (queue.start == queue.end && queue.elements[queue.start] != null)) {
            return queue.elements.length - queue.start + queue.end;
        } else {
            return queue.end - queue.start;
        }
    }

    // Pred: true
    // Post: R = n == 0 ? true : false
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return size(queue) == 0;
    }

    // Pred: true
    // Post: end = start = 0 && elements.length == 3 && for i = 0...elements.length: elements[i] = 0
    public static void clear(final ArrayQueueADT queue) {
        queue.elements = new Object[3];
        queue.start = 0;
        queue.end = 0;
        queue.counts = new HashMap<>();
    }

}
