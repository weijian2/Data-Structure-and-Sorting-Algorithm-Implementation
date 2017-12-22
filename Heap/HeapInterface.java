package Heap;
/**
 *
 * Simple Min Heap interface.
 *
 * boolean offer(int integer);
 * int poll();
 * int peek();
 * int update(int index, int value);
 * int size();
 * boolean isEmpty();
 *
 * @author Weijian Li
 *
 */
public interface HeapInterface {
    /**
     * insert a new interger into min heap.
     * @param value element to be inserted, time is O(logn)
     */
    void offer(int value);

    /**
     * delete min value of this heap., time is O(logn)
     * @return min value of this heap
     */
    int poll();

    /**
     * detect min value of this heap.
     * @return min value of this heap
     */
    int peek();

    /**
     * update specific index's value to given value. Time is O(logn)
     * @param index index of internal heap array
     * @param value new value to be updated
     * @return old value.
     */
    int update(int index, int value);

    /**
     * size of this heap. Time is O(1)
     * @return size of heap
     */
    int size();

    /**
     * detect if this heap is empty. O(1)
     * @return true if empty
     */
    boolean isEmpty();
}
