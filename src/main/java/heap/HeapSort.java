package heap;

import util.Utils;

import java.util.Arrays;

import static util.Utils.swap;

public class HeapSort {

    public static void sort(int[] array) {
        MaxHeap maxHeap = MaxHeap.buildMaxHeap(array);

        for (int i = array.length-1; i >= 1; i--) {
            swap(array, 0, i);
            maxHeap.setHeapSize(maxHeap.getHeapSize()-1);
            maxHeap.maxHeapify(0);
        }
    }

    public static void main(String[] args) {
        testCase(new int[]{5, 13, 2, 25, 7, 17, 20, 8, 4});
    }

    private static void testCase(int[] array) {
        System.out.println("Unsorted : " + Arrays.toString(array));
        HeapSort.sort(array);
        System.out.println("Sorted : " + Arrays.toString(array));
    }
}
