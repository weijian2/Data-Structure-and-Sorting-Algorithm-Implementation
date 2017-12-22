package HashTable;

import java.util.HashMap;

/**
 * simple implementation of hashSet, backed by a HashMap instance.
 * @author Weijian Li
 * @param <E>
 */
public class MyHashSet<E> implements HashSetInterface<E> {
    /**
     * instance variable, HashMap instance
     */
    private HashMap<E, Object> map;
    /**
     * instance variable, no practical sense, only used for all existing keys
     */
    private Object placeHolder;

    /**
     * default constructor.
     */
    public MyHashSet() {
        placeHolder = new Object();
        map = new HashMap<E, Object>();
    }

    @Override
    public boolean add(E e) {
        return map.put(e, placeHolder) == null; // true means didn’t exist before
    }

    @Override
    public boolean remove(E e) {
        return map.remove(e) != null; // true means remove successfully
    }

    @Override
    public boolean contains(E e) {
        return map.containsKey(e);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
