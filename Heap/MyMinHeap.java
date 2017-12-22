package Heap;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * Simple Min Heap implementation based on simple min heap interface.
 *
 * implemented public method
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
public class MyMinHeap implements HeapInterface {
    /**
     * instance variable, internal array which keeps Node as its content.
     */
    private Node[] heapArray;
    /**
     * instance variable, size of this heap.
     */
    private int size;
    /**
     * static variable, default capacity.
     */
    private static final int DEFAULT_CAPACITY = 11;

    /**
     * default constructor, which creates a Heap with the default initial capacity (11).
     */
    public MyMinHeap() {
        this.heapArray = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * constuctor takes an array and then heapify it. O(n)
     * @param array input array
     */
    public MyMinHeap(Node[] array) {
        this.heapArray = array;
        this.size = array.length;
        heapify();
    }

    /**
     * insert a new interger into min heap.
     * @param value element to be inserted, time is O(logn)
     */
    public void offer(int value) {
        // first we need to test if internal array is full already, if it is already full, we enlarge it 50%
        if (size == heapArray.length) {
            heapArray = Arrays.copyOf(heapArray, (int) (heapArray.length * 1.5));
        }
        heapArray[size] = new Node(value);
        percolateUp(size);
        size++;
    }

    /**
     * delete min value of this heap., time is O(logn)
     * @return min value of this heap
     */
    public int poll() {
        // first we need to test if internal array is empty already, if it is already empty, throw exception
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        Node root = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        heapArray[size - 1] = null;
        percolateDown(0);
        size--;
        return root.key;
    }

    /**
     * detect min value of this heap.
     * @return min value of this heap
     */
    public int peek() {
        // first we need to test if internal array is empty already, if it is already empty, throw exception
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }
        return heapArray[0].key;
    }

    /**
     * update specific index's value to given value. Time is O(logn)
     * @param index index of internal heap array
     * @param value new value to be updated
     * @return old value.
     */
    public int update(int index, int value) {
        // check index validation
        if (index < 0 || index > size - 1) {
            throw new NoSuchElementException("Invalid index");
        }
        int oldValue = heapArray[index].key;
        heapArray[index] = new Node(value);
        if (value >= oldValue) {
            percolateDown(index);
        } else {
            percolateUp(index);
        }
        return oldValue;
    }

    /**
     * size of this heap. Time is O(1)
     * @return size of heap
     */
    public int size() {
        return size;
    }

    /**
     * detect if this heap is empty. O(1)
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * private static class, which is the content of heap.
     */
    private static class Node {
        /**
         * instance variable key
         */
        private int key;

        /**
         * constructor which takes an int as its parameter.
         * @param k given key
         */
        Node(int k) {
            this.key = k;
        }
    }

    /**
     * percolate up specific index's element to its proper position
     * @param index specified index
     */
    private void percolateUp(int index) {
        // save newly added element, so that we don't need to write swap() function, which takes 3 copies
        Node element = heapArray[index];
        // find initial value of parent index
        int parentIndex = (index - 1) / 2;
        while (index > 0) {
            if (heapArray[parentIndex].key > element.key) {
                // parent node comes down
                heapArray[index] = heapArray[parentIndex];
                index = parentIndex;
                parentIndex = (index - 1) / 2;
            } else {
                break;
            }
        }
        // for here, index == 0 or heapArray[parentIndex].key <= element.key
        // finally, insert newly added node into proper position
        heapArray[index] = element;
    }

    /**
     * percolate down specific index's element to its proper position
     * @param index specified index
     */
    private void percolateDown(int index) {
        // save element, so that we don't need to write swap() function, which takes 3 copies
        Node element = heapArray[index];
        // we need to "swap" parent with its smaller child
        int smallerChildIndex;
        // while current index has at least left child
        while (index * 2 + 1 <= size - 1) {
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;
            smallerChildIndex = leftChildIndex;
            if (index * 2 + 2 <= size - 1) {
                smallerChildIndex = heapArray[leftChildIndex].key < heapArray[rightChildIndex].key ? leftChildIndex : rightChildIndex;
            }
            if (heapArray[smallerChildIndex].key < element.key) {
                heapArray[index] = heapArray[smallerChildIndex];
                index = smallerChildIndex;
            } else {
                break;
            }
        }
        // put top key into proper location
        heapArray[index] = element;
    }

    /**
     * convert an array to heap, via percolateDown(O(n)), if via percolateUp, which cost O(nlogn)
     * the only place that utilize heapify() is in constructor
     */
    private void heapify() {
        // first we need to find first index that need to perform percolateDown(), which is the last element's parent.
        int beginPoint = (size - 1 - 1) / 2;
        // and from that index, perform percolateDown until index is 0;
        for (int i = beginPoint; i >= 0; i--) {
            percolateDown(i);
        }
    }
}
