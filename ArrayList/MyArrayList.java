package ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementation of ArrayList.
 * @author weijian1 Weijian Li
 * public method add(E e), add(int index), get(), isEmpty(), remove(int index), size(), iterator()
 * @param <AnyType> data type to insert into list
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {
    /**
     * instance variable, how many element current array holds.
     */
    private int size;

    /**
     * instance variable, internal array.
     */
    private Object[] array;

    /**
     * default constructor takes no argument.
     */
    public MyArrayList() {
        this.array = new Object[0];
        this.size = 0;
    }

    /**
     * constructor with given initial capacity.
     * @param initialCapacity initial capacity
     */
    public MyArrayList(int initialCapacity) {
        // check argument
        if (initialCapacity < 0) {
            throw new RuntimeException("array length cannot be negative");
        }
        this.array = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param item element to be appended to this list
     * @return true
     */
    public boolean add(AnyType item) {
        ensureCapacity(size + 1);
        array[size++] = item;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index target index
     * @param item target object
     */
    public void add(int index, AnyType item) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity(size + 1);
        // shift
        for (int i = size; i > index; i--) {
            array[size] = array[size - 1];
        }
        array[index] = item;
        size++;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index target position
     * @return result
     */
    public AnyType get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[index];
        return result;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if arraylist is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index target index
     * @return target
     */
    public AnyType remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[index];
        // shift
        for (int i = index; i < size - 1; i++) {
            array[index] = array[index + 1];
        }
        array[size - 1] = null;
        size--;
        return result;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param item target
     * @return true if target exists
     */
    public boolean remove(AnyType item) {
        if (item == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index target index
     * @param item target item
     * @return old item
     */
    public AnyType set(int index, AnyType item) {
        @SuppressWarnings("unchecked")
        AnyType result = (AnyType) array[index];
        array[index] = item;
        return result;
    }

    /**
     * Returns the number of elements in this list.
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * Make sure array can holds at least minCapacity elements.
     * @param minCapacity min elemnets specified by user
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = array.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity / 2 * 3 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    /**
     * return arraylist iterator
     * @return iterator of this arraylist
     */
    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    /**
     * private nested class implements Iterator interface.
     */
    private class ArrayListIterator implements Iterator<AnyType> {
        /**
         * instance variable, index.
         */
        private int nextIndex;

        /**
         * constructor takes no arguments.
         */
        ArrayListIterator() {
            nextIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return nextIndex < size - 1;
        }
        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            @SuppressWarnings("unchecked")
            AnyType result = (AnyType) array[nextIndex];
            nextIndex++;
            return result;
        }
    }
}
