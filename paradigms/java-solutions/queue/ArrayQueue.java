package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[3];
    private int start, end = 0;

    private void increaseCapacity() {
        if (start == end && elements[start] != null) {
            Object[] tmp = new Object[elements.length * 2];
            System.arraycopy(elements, start, tmp, 0, elements.length - start);
            System.arraycopy(elements, 0, tmp, elements.length - start, end);
            end = elements.length;
            elements = tmp;
            start = 0;
        }
    }

    @Override
    protected void enqueueImpl(Object element) {
        increaseCapacity();
        elements[end] = element;
        end = (end + 1) % elements.length;
    }

    @Override
    protected Object elementImpl() {
        return elements[start];
    }

    @Override
    protected void dequeueImpl(int size) {
        elements[start] = null;
        start = (start + 1) % elements.length;
    }

    @Override
    protected void clearImpl() {
        elements = new Object[3];
        start = end = 0;
    }
}
