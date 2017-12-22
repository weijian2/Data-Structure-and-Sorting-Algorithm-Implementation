package HashTable;

/**
 * HashTable interface. Using Separate chaining(closed addressing) as a way to solve hash collision.
 * @author Weijian Li
 */
public interface HashTableInterface<K, V> {
    /**
     * Returns the number of key-value mappings in this map.
     * @return the number of key-value mappings in this map.
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     * @return true if this map is empty
     */
    boolean isEmpty();

    /**
     * Removes all of the mappings from this map.
     */
    void clear();

    /**
     * Associates the specified value with the specified key in this map.
     * @param key key
     * @param value value
     * @return the original value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * @param key key
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key key
     * @return true if map contains this key
     */
    boolean containsKey(K key);

    /**
     * Returns true if this map maps one or more keys to the specified value.
     * @param value value
     * @return true if map contains this value
     */
    boolean containsValue(V value);

    /**
     * Removes the mapping for the specified key from this map if present.
     * @param key key
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V remove(K key);
}
