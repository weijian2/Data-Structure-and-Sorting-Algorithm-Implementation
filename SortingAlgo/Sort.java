package SortingAlgo;

/**
 * Sort class, contains several static sort method, including bubble sort, selection sort, insertion sort, merge
 * sort and quick sort.
 * @author Weijian Li
 */
public class Sort {
    /**
     * bubble sort, slowest sorting algorithm, O(n^2).
     * @param array input array
     */
    public static int[] bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int out = array.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (array[in] > array[in + 1]) {
                    swap(array, in, in + 1);
                }
            }
        }
        return array;
    }

    /**
     * selection sort, second slowest sorting algorithm, O(n^2).
     * @param array input array
     */
    public static int[] selectionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int out = 0; out < array.length - 1; out++) {
            // initialize min to out
            int min = out;
            for (int in = out + 1; in < array.length; in++) {
                if (array[in] < array[min]) {
                    min = in;
                }
            }
            swap(array, out, min);
        }
        return array;
    }

    /**
     * insertion sort, O(n^2).
     * @param array input array
     */
    public static int[] insertionSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int cur = array[i];
            int j = i - 1;
            while (j >= 0 && cur < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = cur;
        }
        return array;
    }

    /**
     * merge sort, O(nlogn).
     * @param array input array
     */
    public static int[] mergeSort(int[] array) {
        // check corner case
        if (array == null || array.length <= 1) {
            return array;
        }
        int[] helperArray = new int[array.length];
        mergeSortHelper(array, 0, array.length - 1, helperArray);
        return array;
    }

    /**
     * recursive helper function.
     * @param array input array
     * @param left left index
     * @param right right index
     * @param helperArray helper array
     */
    private static void mergeSortHelper(int[] array, int left, int right, int[] helperArray) {
        // base case
        if (left >= right) {
            return;
        }
        // recursive rule
        int mid = left + (right - left) / 2;
        mergeSortHelper(array, left, mid, helperArray);
        mergeSortHelper(array, mid + 1, right, helperArray);
        merge(array, left, mid, right, helperArray);
    }

    /**
     * merge function.
     * @param array input array
     * @param left left index
     * @param mid mid point
     * @param right right index
     * @param helperArray helper array
     */
    private static void merge(int[] array, int left, int mid, int right, int[] helperArray) {
        // first we need to copy all elements in original array to helperArray
        for (int i = left; i <= right; i++) {
            helperArray[i] = array[i];
        }
        // then we need to merge
        int leftBegin = left;
        int rightBegin = mid + 1;
        int index = left;
        while (leftBegin <= mid && rightBegin <= right) {
            if (helperArray[leftBegin] < helperArray[rightBegin]) {
                array[index++] = helperArray[leftBegin++];
            } else {
                array[index++] = helperArray[rightBegin++];
            }
        }
        // for here, leftBegin > mid || rightBegin > right
        while (leftBegin <= mid) {
            array[index++] = helperArray[leftBegin++];
        }
    }

    /**
     * quick sort, O(nlogn).
     * @param array input array
     */
    public static int[] quickSort(int[] array) {
        // check corner case
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSortHelper(array, 0, array.length - 1);
        return array;
    }

    /**
     * QuickSort helper function.
     * @param array input array
     * @param left left index
     * @param right right index
     */
    private static void quickSortHelper(int[] array, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        // recursive rule
        int pivotIndex = partition(array, left, right);
        quickSortHelper(array, left, pivotIndex - 1);
        quickSortHelper(array, pivotIndex + 1, right);
    }

    /**
     * Partition function.
     * @param array input array
     * @param left left index
     * @param right right index
     * @return pivot index
     */
    private static int partition(int[] array, int left, int right) {
        // first we need to find pivot index
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivotValue = array[pivotIndex];
        // swap pivotIndex to the end first
        swap(array, pivotIndex, right);
        // all numbers less than pivot should be in lhs of pivot, all numbers larger or equal to pivot should be in rhs of pivot
        int leftBegin = left;
        int rightBegin = right - 1;
        while (leftBegin <= rightBegin) {
            if (array[leftBegin] < pivotValue) {
                leftBegin++;
            } else {
                swap(array, leftBegin, rightBegin--);
            }
        }
        // swap back pivot index
        swap(array, leftBegin, right);
        // return pivot index
        return leftBegin; // or we can return rightBegin + 1
    }

    /**
     * Swap two element in given array.
     * @param array input array
     * @param i index1
     * @param j index2
     */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
