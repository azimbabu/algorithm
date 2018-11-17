package heap;

import util.Utils;

import java.util.Arrays;

import static util.Utils.swap;

public class MaxHeap {

    private int[] array;
    private int heapSize;

    private MaxHeap(int[] array) {
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

    public void maxHeapify(int i) {
        int left = left(i);
        int right = right(i);

        int largest = i;
        if (left < heapSize && array[left] > array[i]) {
            largest = left;
        }

        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(array, i, largest);
            maxHeapify(largest);
        }
    }

    public void maxHeapifyIterative(int i) {
        int previous = -1, current = i;
        while (previous != current) {
            int left = left(current);
            int right = right(current);

            int largest = current;
            if (left < heapSize && array[left] > array[current]) {
                largest = left;
            }

            if (right < heapSize && array[right] > array[largest]) {
                largest = right;
            }

            previous = current;
            current = largest;
        }
    }

    public static MaxHeap buildMaxHeap(int[] array) {
        MaxHeap maxHeap = new MaxHeap(array);
        maxHeap.heapSize = array.length;
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeap.maxHeapify(i);
        }
        return maxHeap;
    }

    public static void main(String[] args) {
        int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        System.out.println("Initial : " + Arrays.toString(array));

        MaxHeap maxHeap = MaxHeap.buildMaxHeap(array);
        System.out.println("MaxHeap : " + Arrays.toString(maxHeap.array));

    }
}
