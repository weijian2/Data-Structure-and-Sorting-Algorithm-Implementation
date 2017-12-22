package HashTable;

import java.util.Arrays;

/**
 * A hashtable implementation of map, using Separate chaining(closed addressing) as a way to solve hash collision.
 * @author WEIJIAN LI
 *
 * supported operations:
 * size()
 * isEmpty
 * clear()
 * put(K key, V value)
 * get(K key)
 * containsKey(K key)
 * containsValue(V value)
 * remove(K key)
 */
public class MyHashMap<K, V> implements HashTableInterface<K, V> {
    /**
     * instance variable, internal array.
     */
    private Object[] buckets;
    /**
     * instance variable, count how many key-value pairs are actually stored in the HashMap.
     */
    private int size;
    /**
     * instance variable, actual size of this map.
     */
    private double loadFactor;
    /**
     * default capacity.
     */
    public static final int DEFAULT_CAPACITY = 16;
    /**
     * default load factor.
     */
    public static final double DEFAULT_LOAD_FACTOR = 0.75;

    /**
     * default constructor.
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }
    /**
     * constructor with capacity and load factor specified by user.
     * @param cap user specified capacity
     * @param loadFactor user specified load factor
     */
    public MyHashMap(int cap, double loadFactor) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity cannot be negative");
        }
        this.buckets = new Object[cap];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(buckets, null);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int hashValue = getHashValue(key);
        int index = getIndex(hashValue);
        @SuppressWarnings("unchecked")
        Entry<K, V> entry  = (Entry<K, V>)buckets[index];
        while (entry != null) {
            // more elegant logic
            K currentKey = entry.key;
            if (currentKey == key || (currentKey != null && currentKey.equals(key))) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        // corner case
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                @SuppressWarnings("unchecked")
                Entry<K, V> cur = (Entry<K, V>)buckets[i];
                while (cur != null) {
                    if (cur.value.equals(value)) {
                        return true;
                    }
                    cur = cur.next;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int hashValue = getHashValue(key);
        int index = getIndex(hashValue);
        @SuppressWarnings("unchecked")
        Entry<K, V> entry = (Entry<K, V>)buckets[index];
        while (entry != null) {
            if (entry.key == null) {
                if (key == null) {
                    return entry.value;
                }
            } else if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hashValue = getHashValue(key);
        int index = getIndex(hashValue);
        @SuppressWarnings("unchecked")
        Entry<K, V> entry = (Entry<K, V>)buckets[index];
        Entry<K, V> head = (Entry<K, V>)buckets[index];
        while (head != null) {
            if (head.key == key || (head.key != null && head.key.equals(key))) {
                return head.setValue(value);
            }
            head = head.next;
        }
        Entry<K, V> newEntry = new Entry<K, V>(key, value);
        newEntry.next = entry;
        buckets[index] = newEntry;
        size++;
        if (needRehashing()) {
            rehashing();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int hashValue = getHashValue(key);
        int index = getIndex(hashValue);
        @SuppressWarnings("unchecked")
        Entry<K, V> entry  = (Entry<K, V>)buckets[index];
        if (entry == null) {
            return null;
        } else {
            // do remove, this is actually remove node in linked list, node head may change!
            if (entry.key == key || (entry.key != null && entry.key.equals(key))) {
                buckets[index] = entry.next;
                size--;
                return entry.value;
            } else {
                Entry<K, V> cur = entry.next;
                Entry<K, V> prev = entry;
                while (cur != null) {
                    if (cur.key == key || (cur.key != null && cur.key.equals(key))) {
                        prev.next = cur.next;
                        size--;
                        return cur.value;
                    }
                    prev = cur;
                    cur = cur.next;
                }
                return null;
            }
        }
    }

    /**
     * return true if current load factor larger or equal than default/user specified load factor.
     * @return true if current load factor larger or equal than default/user specified load factor
     */
    private boolean needRehashing() {
        double ratio = size * 1.0 / buckets.length;
        return ratio >= loadFactor;
    }

    /**
     * rehash function
     */
    private void rehashing() {
        Object[] newBuckets = new Object[buckets.length * 2];
        for (int i = 0; i < buckets.length; i++) {
            @SuppressWarnings("unchecked")
            Entry<K, V> entry = (Entry<K, V>)buckets[i];
            if (entry != null) {
                while (entry != null) {
                    int hashValue = getHashValue(entry.key);
                    int index = hashValue % newBuckets.length;
                    Entry<K, V> copy = new Entry<K, V>(entry.key, entry.value);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = copy;
                    } else {
                        copy.next = (Entry<K, V>)newBuckets[index];
                        newBuckets[index] = copy;
                    }
                    entry = entry.next;
                }
            }
        }
        buckets = newBuckets; // old buckets will be destroied by GC in java
    }

    /**
     * calculate hash value of given key.
     * @param key key
     * @return hash value of this key
     */
    private int getHashValue(K key) {
        if (key == null) {
            return 0;
        }
        int hashCode = key.hashCode();
        // return hashCode > 0 ? hashCode : Math.abs(hashCode);
        // return hashCode & Integer.MAX_VALUE;
        return hashCode & 0x7fffffff; // (0x7fffffff 是16进制数，7是0111，f是1111), bit operation 效率最高
    }

    /**
     * transfer hashValue to index in the array.
     * @param hashValue calculated hash value of object
     * @return index in the array
     */
    private int getIndex(int hashValue) {
        return hashValue % buckets.length;
    }

    /**
     * private static class, used only in this class
     * @param <K>
     * @param <V>
     */
    private static class Entry<K, V> {
        /**
         * instance variable, key is final since we don't want to change it once assigned.
         */
        private final K key;
        /**
         * instance variable, value.
         */
        private V value;
        /**
         * instance variable, next variable.
         */
        private Entry<K, V> next;

        /**
         * constructor with key and value.
         * @param key key
         * @param value value
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * set new value and return original value
         * @param newValue value to be setted
         * @return old value
         */
        private V setValue(V newValue) {
            V pre = value;
            value = newValue;
            return pre;
        }
    }
}
