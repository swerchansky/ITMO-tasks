package queue;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    private int size = 0;
    protected Map<Object, Integer> counts = new HashMap<>();

    protected abstract void enqueueImpl(Object element);

    protected abstract Object elementImpl();

    protected abstract void dequeueImpl(int size);

    protected abstract void clearImpl();

    // :NOTE:/2 too slow
    @Override
    public int countIf(Predicate<Object> pred) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            Object element = dequeue();
            if (pred.test(element)) {
                count++;
            }
            enqueue(element);
        }
        return count;
    }

    @Override
    public int count(final Object element) {
        return counts.getOrDefault(element, 0);
    }

    @Override
    public void enqueue(Object element) {
        Objects.requireNonNull(element);

        int num = counts.getOrDefault(element, 0);
        counts.put(element, num + 1);
        size++;
        enqueueImpl(element);
    }

    @Override
    public Object element() {
        assert size > 0;
        return elementImpl();
    }

    @Override
    public Object dequeue() {
        assert size > 0;

        size--;
        Object result = elementImpl();
        int num = counts.getOrDefault(result, 0);
        counts.put(result, num - 1);
        dequeueImpl(size);
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        counts = new HashMap<>();
        clearImpl();
    }
}
