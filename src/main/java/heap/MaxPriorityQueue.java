package heap;

import util.Utils;

import static heap.MaxHeap.left;
import static heap.MaxHeap.parent;
import static heap.MaxHeap.right;
import static util.Utils.swap;

public class MaxPriorityQueue {

    private MaxHeap maxHeap;
    private int[] array;

    public MaxPriorityQueue(int[] array) {
        this.array = array;
        maxHeap = MaxHeap.buildMaxHeap(array);
    }

    public int heapMaximum() {
        checkEmpty();
        return array[0];
    }

    public int heapExtractMax() {
        checkEmpty();

        int max = array[0];
        array[0] = array[maxHeap.getHeapSize()-1];
        maxHeap.setHeapSize(maxHeap.getHeapSize()-1);
        maxHeap.maxHeapify(0);
        return max;
    }

    public void heapIncreaseKey(int i, int key) {
        checkEmpty();

        if (key < array[i]) {
            throw new RuntimeException("New key is smaller than current key");
        }

        array[i] = key;
        while (i > 0 && array[i] > array[parent(i)]) {
            swap(array, i, array[parent(i)]);
            i = parent(i);
        }
    }

    /**
     * Each exchange operation of HEAP-INCREASE-KEY typically requires three assignments.
     * Show how to use the idea of the inner loop of INSERTION-SORT
     * to reduce the three assignments down to just one assignment.
     * @param i
     * @param key
     */
    public void heapIncreaseKeyInsertionSortStyle(int i, int key) {
        checkEmpty();

        if (key < array[i]) {
            throw new RuntimeException("New key is smaller than current key");
        }

        while (i > 0 && key > array[parent(i)]) {
            array[parent(i)] = array[i];
            i = parent(i);
        }
        array[i] = key;
    }

    public void maxHeapInsert(int key) {
        checkHeapSize();

        maxHeap.setHeapSize(maxHeap.getHeapSize()+1);
        array[maxHeap.getHeapSize()-1] = Integer.MIN_VALUE;
        heapIncreaseKey(maxHeap.getHeapSize()-1, key);
    }

    /**
     * The operation HEAP-DELETE (A,i) deletes the item in node i from heap A.
     * Give an implementation of HEAP-DELETE that runs in O(lg n) time for an n-element max heap.
     * @param i
     * @return
     */
    public int maxHeapDelete(int i) {
        checkEmpty();

        if (maxHeap.getHeapSize() <= i) {
            throw new RuntimeException("Not found");
        }

        int value = array[i];
        array[i] = array[maxHeap.getHeapSize()-1];
        maxHeap.setHeapSize(maxHeap.getHeapSize()-1);
        maxHeap.maxHeapify(i);
        return value;
    }

    private void checkHeapSize() {
        if (maxHeap.getHeapSize() == array.length) {
            // alternatively we can increase the array size or use an array list instead of an array.
            throw new RuntimeException("Heap overflow");
        }
    }

    private void checkEmpty() {
        if (maxHeap.getHeapSize() == 0) {
            throw new RuntimeException("Heap underflow");
        }
    }

    public static void main(String[] args) {
        heapExtractMaxDemo();
        maxHeapInsertDemo();
    }

    private static void heapExtractMaxDemo() {
        System.out.println("HeapExtractMax Demo: ");
        MaxPriorityQueue priorityQueue = new MaxPriorityQueue(new int[]{15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1});

        while (priorityQueue.maxHeap.getHeapSize() > 0) {
            System.out.println("Next max : " + priorityQueue.heapExtractMax());
        }
    }

    private static void maxHeapInsertDemo() {
        System.out.println("MaxHeapInsert Demo: ");
        MaxPriorityQueue priorityQueue = new MaxPriorityQueue(new int[]{15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1, Integer.MIN_VALUE});
        priorityQueue.maxHeap.setHeapSize(priorityQueue.maxHeap.getHeapSize()-1);   // last position is available now
        priorityQueue.maxHeapInsert(10);

        while (priorityQueue.maxHeap.getHeapSize() > 0) {
            System.out.println("Next max : " + priorityQueue.heapExtractMax());
        }
    }
}
