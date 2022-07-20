package queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// Model: a[1]...a[n]
// Invariant: n >= 0 && for i = 1...n: a[i] != null
public class ArrayQueueModule {
    private static Object[] elements = new Object[3];
    private static int start, end = 0;
    private static Map<Object, Integer> counts = new HashMap<>();

    // Pred: n == elements.length
    // Post: elements.length' = elements.length * 2 && start' = 0 && end' = elements.length && n' = n &&
    // for i = 0...n: elements[i]' = elements[(i+start) % elements.length] &&
    // for i = n+1...elements.length': elements[i] = null
    private static void increaseCapacity() {
        if (start == end && elements[start] != null) {
            Object[] tmp = new Object[elements.length * 2];
            System.arraycopy(elements, start, tmp, 0, elements.length - start);
            System.arraycopy(elements, 0, tmp, elements.length - start, end);
            end = elements.length;
            elements = tmp;
            start = 0;
        }
    }

    // Pred: True
    // Post: R = counts.getOrDefault(element, 0)
    public static int count(final Object element) {
        return counts.getOrDefault(element, 0);
    }

    // Pred: element != null
    // Post: n' = n + 1 && elements[end] = element && end' = (end + 1) % elements.length &&
    // counts.get(element)' = counts.get(element) + 1
    public static void enqueue(final Object element) {
        Objects.requireNonNull(element);
        increaseCapacity();
        elements[end] = element;
        end = (end + 1) % elements.length;
        int num = counts.getOrDefault(element, 0);
        counts.put(element, num + 1);
    }

    // Pred: !isEmpty()
    // Post: R = elements[start]
    public static Object element() {
        assert !isEmpty();
        return elements[start];
    }

    // Pred: !isEmpty()
    // Post: R = elements[start] && start' = (start + 1) % elements.length && elements[start] = null &&
    // counts.get(element)' = counts.get(element) - 1
    public static Object dequeue() {
        assert !isEmpty();
        Object value = element();
        elements[start] = null;
        start = (start + 1) % elements.length;
        int num = counts.getOrDefault(value, 0);
        counts.put(value, num - 1);
        return value;
    }

    // Pred: true
    // Post: R = n
    public static int size() {
        if ((start > end) || (start == end && elements[start] != null)) {
            return elements.length - start + end;
        } else {
            return end - start;
        }
    }

    // Pred: true
    // Post: R = n == 0 ? true : false
    public static boolean isEmpty() {
        return size() == 0;
    }

    // Pred: true
    // Post: end = start = 0 && elements.length == 3 && for i = 0...elements.length: elements[i] = 0
    public static void clear() {
        elements = new Object[3];
        start = 0;
        end = 0;
        counts = new HashMap<>();
    }

}
