package queue;

import base.Selector;
import base.TestCounter;

import java.util.List;
import java.util.function.Consumer;

import static queue.Queues.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public final class ArrayQueueTest {
    public static final Selector SELECTOR = new Selector(ArrayQueueTest.class)
            .variant("Base", variant(QueueModel.class, d -> () -> d))
            .variant("Count", variant(CountModel.class, d -> () -> d, COUNT))
            .variant("Index", variant(IndexModel.class, d -> () -> d, INDEX))
            .variant("DequeCount", variant(DequeCountModel.class, (DequeChecker<DequeCountModel>) d -> () -> d, DEQUE_COUNT))
            .variant("DequeIndex", variant(DequeIndexModel.class, (DequeChecker<DequeIndexModel>) d -> () -> d, DEQUE_INDEX))
            ;

    private ArrayQueueTest() {
    }

    public static void main(final String... args) {
        SELECTOR.main(args);
    }

    /* package-private */ static <M extends Queues.QueueModel, T extends Queues.QueueChecker<M>> Consumer<TestCounter> variant(
            final Class<M> type,
            final T tester,
            final Queues.Splitter<M> splitter
    ) {
        return new ArrayQueueTester<>(type, tester, splitter)::test;
    }

    /* package-private */ static <M extends Queues.QueueModel, T extends Queues.QueueChecker<M>> Consumer<TestCounter> variant(
            final Class<M> type,
            final T tester
    ) {
        return variant(type, tester, (t, q, r) -> List.of());
    }
}
