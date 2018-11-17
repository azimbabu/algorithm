package heap;

import static heap.MinHeap.parent;
import static util.Utils.swap;

public class MinPriorityQueue {
    private MinHeap minHeap;
    private int[] array;

    public MinPriorityQueue(int[] array) {
        this.array = array;
        minHeap = MinHeap.buildMinHeap(array);
    }

    public int heapMinimum() {
        checkEmpty();
        return array[0];
    }

    public int heapExtractMin() {
        checkEmpty();

        int min = array[0];
        array[0] = array[minHeap.getHeapSize()-1];
        minHeap.setHeapSize(minHeap.getHeapSize()-1);
        minHeap.minHeapify(0);
        return min;
    }

    public void heapDecreaseKey(int i, int key) {
        checkEmpty();

        if (key > array[i]) {
            throw new RuntimeException("New key is larger than current key");
        }

        array[i] = key;
        while (i > 0 && array[i] < array[parent(i)]) {
            swap(array, i, array[parent(i)]);
            i = parent(i);
        }
    }

    public void minHeapInsert(int key) {
        checkHeapSize();

        minHeap.setHeapSize(minHeap.getHeapSize()+1);
        array[minHeap.getHeapSize()-1] = Integer.MAX_VALUE;
        heapDecreaseKey(minHeap.getHeapSize()-1, key);
    }

    private void checkHeapSize() {
        if (minHeap.getHeapSize() == array.length) {
            // alternatively we can increase the array size or use an array list instead of an array.
            throw new RuntimeException("Heap overflow");
        }
    }

    private void checkEmpty() {
        if (minHeap.getHeapSize() == 0) {
            throw new RuntimeException("Heap underflow");
        }
    }

    public static void main(String[] args) {
        heapExtractMinDemo();
        minHeapInsertDemo();
    }

    private static void heapExtractMinDemo() {
        System.out.println("HeapExtractMin Demo: ");
        MinPriorityQueue priorityQueue = new MinPriorityQueue(new int[]{15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1});

        while (priorityQueue.minHeap.getHeapSize() > 0) {
            System.out.println("Next max : " + priorityQueue.heapExtractMin());
        }
    }

    private static void minHeapInsertDemo() {
        System.out.println("MinHeapInsert Demo: ");
        MinPriorityQueue priorityQueue = new MinPriorityQueue(new int[]{15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1, Integer.MAX_VALUE});
        priorityQueue.minHeap.setHeapSize(priorityQueue.minHeap.getHeapSize()-1);   // last position is available now
        priorityQueue.minHeapInsert(10);

        while (priorityQueue.minHeap.getHeapSize() > 0) {
            System.out.println("Next max : " + priorityQueue.heapExtractMin());
        }
    }
}
