package Queue;

/**
 * Array-based implementation of queue.
 * @author Weijian Li
 * @param <AnyType>
 */
public class MyQueue<AnyType> implements QueueInterface<AnyType> {
    /**
     * internal array, it is Object type because we cannot initiate array without giving this array a
     * known type in compile type.
     */
    private Object[] array;
    /**
     * front pointer of queue.
     */
    private int front;
    /**
     * back pointer of queue.
     */
    private int back;
    /**
     * size of this queue.
     */
    private int size;
    /**
     * default capacity of this queue
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * constructor of this queue
     */
    public MyQueue() {
        this.front = 0;
        this.back = -1;
        this.size = 0;
        this.array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * push a new element into the tail of this queue. aka enqueue, use back pointer.
     * @param item element waited to be pushed
     */
    @Override
    public void enqueue(AnyType item) {
        if (size == array.length) {
            throw new RuntimeException("queue is full!");
        }
        back = (back + 1) % array.length;
        array[back] = item;
        size++;
    }

    /**
     * pop element from front of this queue. aka dequeue, use front pointer.
     * @return front element of this queue
     */
    @Override
    public AnyType dequeue() {
        if (size == 0) {
            throw new RuntimeException("queue is empty!");
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return result;
    }

    /**
     * peek element from front of this queue.
     * @return front element of this queue
     */
    @Override
    public AnyType peekFront() {
        if (size == 0) {
            throw new RuntimeException("queue is empty!");
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[front];
        return result;
    }

    /**
     * detect if this queue is empty or not.
     * @return true if this queue is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
