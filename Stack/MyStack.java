package Stack;

import java.util.NoSuchElementException;

/**
 * Array-based implementation of stack.
 * @author weijian1 Weijian Li
 * @param <AnyType>
 */
public class MyStack<AnyType> implements StackInterface<AnyType> {
    /**
     * internal array, it is Object type because we cannot initiate array without giving this array a
     * known type in compile type.
     */
    private Object[] array;
    /**
     * top pointer of this stack.
     */
    private int top;
    /**
     * capacity of this stack, cannot change, it is static because we need to use it in second constructor
     * without initiating stack.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * default constructor without argument.
     */
    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * construct by given capacity.
     * @param capacity user defined capacity
     */
    public MyStack(int capacity) {
        this.top = -1;
        if (capacity <= 0) {
            this.array = new Object[DEFAULT_CAPACITY];
        } else {
            this.array = new Object[capacity];
        }
    }

    /**
     * Return true if stack is empty.
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Push a new item into stack.
     * @param item item waited to be pushed
     * @return true if push success
     */
    @Override
    public boolean push(AnyType item) {
        if (top == array.length - 1) {
            return false;
        }
        array[++top] = item;
        return true;
    }

    /**
     * Pop an element out of stack.
     * @return top elment of this stack
     */
    @Override
    public AnyType pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        @SuppressWarnings("unckecked")
        AnyType result = (AnyType) array[top];
        array[top--] = null;
        return result;
    }

    /**
     * Peek element on top of the stack.
     * @return top element
     */
    @Override
    public AnyType peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[top];
        return result;
    }
}
