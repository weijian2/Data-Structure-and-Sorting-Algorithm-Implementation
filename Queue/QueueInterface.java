package Queue;
/**
 * interface of queue, all method should be O(1)
 * @param <AnyType>
 */
public interface QueueInterface<AnyType> {
    void enqueue(AnyType item);
    AnyType dequeue();
    AnyType peekFront();
    boolean isEmpty();
}
