package heap;

import java.util.Arrays;

import static util.Utils.swap;

public class MinHeap {

    private int[] array;
    private int heapSize;

    private MinHeap(int[] array) {
        this.array = array;
        this.heapSize = 0;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    public static int parent(int i) {
        return (i - 1)/2;
    }

    public static int left(int i) {
        return 2 * i + 1;
    }

    public static int right(int i) {
        return 2 * i + 2;
    }

    public void minHeapify(int i) {
        int left = left(i);
        int right = right(i);

        int smallest = i;
        if (left < heapSize && array[left] < array[i]) {
            smallest = left;
        }

        if (right < heapSize && array[right] < array[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(array, i, smallest);
            minHeapify(smallest);
        }
    }

    public void minHeapifyIterative(int i) {
        int previous = -1, current = i;
        while (previous != current) {
            int left = left(current);
            int right = right(current);

            int smallest = current;
            if (left < heapSize && array[left] < array[current]) {
                smallest = left;
            }

            if (right < heapSize && array[right] < array[smallest]) {
                smallest = right;
            }

            previous = current;
            current = smallest;
        }
    }

    public static MinHeap buildMinHeap(int[] array) {
        MinHeap minHeap = new MinHeap(array);
        minHeap.heapSize = array.length;
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            minHeap.minHeapify(i);
        }
        return minHeap;
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        System.out.println("Initial : " + Arrays.toString(array));

        MinHeap minHeap = MinHeap.buildMinHeap(array);
        System.out.println("MinHeap : " + Arrays.toString(minHeap.array));

    }
}
