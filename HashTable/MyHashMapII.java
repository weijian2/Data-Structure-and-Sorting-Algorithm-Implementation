package HashTable;

/**
 * HashTable Implementation with linear probing.
 *
 * @author Weijian Li
 */
public class MyHashMapII implements MyHTInterface {
    /**
     * internal array of hash table.
     */
    private DataItem[] hashArray;
    /**
     * default capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * default load factor.
     */
    private static final double LOAD_FACTOR = 0.5;
    /**
     * DataItem marker to mark whether a DataItem is deleted or not.
     */
    private static final DataItem DELETED = new DataItem("DELETED");
    /**
     * how many DataItem this hash table contains.
     */
    private int size;
    /**
     * how many collisions happens in this hash table, collision means "two different keys map to the same hash value.".
     */
    private int numOfCollisions;

    /**
     * default constructor with no initial capacity.
     */
    public MyHashMapII() {
        this.hashArray = new DataItem[DEFAULT_CAPACITY];
    }

    /**
     * constructor with user specified capacity.
     * @param initialCapacity user specified capacity
     */
    public MyHashMapII(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new RuntimeException("initialCapacity cannot less or equal to 0");
        }
        this.hashArray = new DataItem[initialCapacity];
    }

    /**
     * Inserts a new String value (word).
     * Frequency of each word to be stored too.
     * @param value String value to add
     */
    public void insert(String value) {
        int index = hashValue(value);
        boolean hasCollision = false;
        while (hashArray[index] != null && hashArray[index] != DELETED) {
            if (hashArray[index].value.equals(value)) {
                hashArray[index].frequency++;
                return;
            }
            if (hashFunc(hashArray[index].value) % hashArray.length == hashFunc(value) % hashArray.length) {
                hasCollision = true;
            }
            index = (index + 1) % hashArray.length;
        }
        DataItem item = new DataItem(value);
        hashArray[index] = item;
        size++;
        if (needRehashing()) {
            rehash();
        }
        if (hasCollision) {
            numOfCollisions++;
        }
    }

    /**
     * Removes and returns removed value.
     * @param key String to remove
     * @return value that is removed. If not found, return null
     */
    public String remove(String key) {
        int index = hashValue(key);
        String result = null;
        while (hashArray[index] != null && hashArray[index] != DELETED) {
            if (hashArray[index].value.equals(key)) {
                hashArray[index].frequency--;
                if (hashArray[index].frequency == 0) {
                    size--;
                    result = hashArray[index].value;
                    hashArray[index] = DELETED;
                }
                return result;
            }
            index = (index + 1) % hashArray.length;
        }
        return result;
    }

    /**
     * Returns true if value is contained in the table.
     * @param key String key value to search
     * @return true if found, false if not found.
     */
    public boolean contains(String key) {
        int index = hashValue(key);
        while (hashArray[index] != null) {
            if (hashArray[index].value.equals(key)) {
                return true;
            }
            index = (index + 1) % hashArray.length;
        }
        return false;
    }

    /**
     * Returns the size, number of items, of the table.
     * @return the number of items in the table
     */
    public int size() {
        return size;
    }

    /**
     * Returns the hash value of a String.
     * @param value value for which the hash value should be calculated
     * @return int hash value of a String
     */
    public int hashValue(String value) {
        int hashValue = hashFunc(value);
        return hashValue % hashArray.length;
    }

    /**
     * Returns the frequency of a key String.
     * @param key string value to find its frequency
     * @return frequency value if found. If not found, return 0
     */
    public int showFrequency(String key) {
        int index = hashValue(key);
        while (hashArray[index] != null) {
            if (hashArray[index].value.equals(key)) {
                return hashArray[index].frequency;
            }
            index = (index + 1) % hashArray.length;
        }
        return 0;
    }

    /**
     * Displays the values of the table.
     * If an index is empty, it shows **
     * If previously existed data item got deleted, then it should show #DEL#
     */
    public void display() {
        for (DataItem item : hashArray) {
            if (item == null) {
                System.out.print(" **");
            } else if (item == DELETED) {
                System.out.print(" #DEL#");
            } else {
                StringBuilder result = new StringBuilder();
                result.append(" ");
                result.append("[");
                result.append(item.value);
                result.append(", ");
                result.append(item.frequency);
                result.append("]");
                System.out.print(result);
            }
        }
        System.out.println();
    }

    /**
     * Returns the number of collisions in relation to insert and rehash.
     * When rehashing process happens, the number of collisions should be properly updated.
     *
     * The definition of collision is "two different keys map to the same hash value."
     * Be careful with the situation where you could overcount.
     * Try to think as if you are using separate chaining.
     * "How would you count the number of collisions?" when using separate chaining.
     * @return number of collisions
     */
    public int numOfCollisions() {
        return numOfCollisions;
    }

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value
     * 2. compress into the table using modular hashing (division method)
     *
     * Helper method to hash a string for English lowercase alphabet and blank,
     * we have 27 total. But, you can assume that blank will not be added into
     * your table. Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     * @param input input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        }
        int hashValue = 0;
        for (int i = 0; i < input.length(); i++) {
            hashValue = hashValue * 27 + input.charAt(i) - 96;
            hashValue = hashValue % hashArray.length;
        }
        hashValue = hashValue & 0x7fffffff;
        return hashValue;
    }

    /**
     * test if need rehash.
     * @return true if need
     */
    private boolean needRehashing() {
        double ratio = size * 1.0 / hashArray.length;
        return ratio >= LOAD_FACTOR;
    }

    /**
     * doubles array length and rehash items whenever the load factor is reached.
     */
    private void rehash() {
        int oldLength = hashArray.length;
        int newLength = nextPrime(2 * oldLength);
        int collisions = 0;
        DataItem[] buckets = new DataItem[newLength];
        for (DataItem item : hashArray) {
            if (item != null && item != DELETED) {
                String key = item.value;
                int hashValue = 0;
                for (int i = 0; i < key.length(); i++) {
                    hashValue = hashValue * 27 + key.charAt(i) - 96;
                    hashValue = hashValue % newLength;
                }

                int index = hashValue % newLength;

                if (buckets[index] != null) {
                    collisions++;
                    while (buckets[index] != null) {
                        index = (index + 1) % newLength;
                    }
                }
                buckets[index] = item;
            }
        }
        hashArray = buckets;
        numOfCollisions = collisions;
    }

    /**
     * calculate first prime number larger than input length.
     * @param length input length
     * @return first prime number larger than input length
     */
    private int nextPrime(int length) {
        while (!isPrime(length)) {
            length++;
        }
        return length;
    }

    /**
     * test if a given number is prime number or not.
     * @param number input number
     * @return true if a given number is prime number
     */
    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;


        /**
         * constructor with a value.
         * @param input String representation of this value
         */
        private DataItem(String input) {
            this.value = input;
            this.frequency = 1;
        }
    }
}
