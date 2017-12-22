package HashTable;

/**
 * HashSet interface.
 * @author Weijian Li
 */
public interface HashSetInterface<E> {
    /**
     * Returns the number of elements in this set (its cardinality).
     * @return the number of elements in this set (its cardinality).
     */
    int size();

    /**
     * Returns true if this set contains no elements.
     * @return true if this set is empty
     */
    boolean isEmpty();

    /**
     * Removes all of the elements from this set.
     */
    void clear();

    /**
     * Adds the specified element to this set if it is not already present.
     * @param e elements need to be added
     * @return true if added successfully or false if already existed
     */
    boolean add(E e);

    /**
     * Returns true if this set contains the specified element.
     * @param e element waited to be test.
     * @return true if this set contains this element
     */
    boolean contains(E e);

    /**
     * Removes the specified element from this set if it is present.
     * @param e element waited to be removed
     * @return true if remove successfully
     */
    boolean remove(E e);
}
