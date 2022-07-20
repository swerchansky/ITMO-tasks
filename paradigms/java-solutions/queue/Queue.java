package queue;

import java.util.function.Predicate;

// Model: a[1]...a[n]
// Invariant: n >= 0 && for i = 1...n: a[i] != null
public interface Queue {

    // Pred: element != null
    // Post: n' = n + 1 && a[n'] = element && for i in 1..n: a'[i] = a[i]
    void enqueue(Object element);

    // Pred: n > 0
    // Post: R = a[1] && for i in 1..n: a'[i] = a[i] && n' = n
    Object element();

    // Pred: n > 0
    // Post: n' = n-1 && R = a[1] && for i=1...n' && a'[i] = a[i+1]
    Object dequeue();

    // Pred: true
    // Post: R = n && for i in 1..n: a'[i] = a[i] && n' = n
    int size();

    // Pred: true
    // Post: R = (n == 0) && for i in 1..n: a'[i] = a[i] && n' = n
    boolean isEmpty();

    // Pred: true
    // Post: n = 0
    void clear();

    // Pred: n > 0
    // Post: R = number of elements of predicate && for i in 1..n: a'[i] = a[i] && n' = n
    int countIf(Predicate<Object> pred);

    int count(final Object element);
}
