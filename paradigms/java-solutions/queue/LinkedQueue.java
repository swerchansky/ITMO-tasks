package queue;

public class LinkedQueue extends AbstractQueue {
    private Node head, tail;

    @Override
    protected void enqueueImpl(Object element) {
        if (tail == null) {
            tail = new Node(element, null);
            head = tail;
        } else {
            tail.next = new Node(element, null);
            tail = tail.next;
        }
    }

    @Override
    public Object elementImpl() {
        return head.element;
    }

    @Override
    protected void dequeueImpl(int size) {
        head = head.next;
        if (size == 0) {
            tail = null;
        }
    }

    @Override
    public void clearImpl() {
        head = null;
        tail = null;
    }

    protected static class Node {
        protected Object element;
        protected Node next;

        public Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }


}
