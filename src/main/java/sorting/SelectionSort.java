package sorting;

import util.Utils;

import java.util.Arrays;

import static util.Utils.swap;

public class SelectionSort {

    public static void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    public static void main(String[] args) {
        testCase(new int[] {5, 2, 4, 6, 1 ,3});
        testCase(new int[] {31, 41, 59, 26, 41, 58});
    }

    private static void testCase(int[] array) {
        System.out.println("Unsorted=" + Arrays.toString(array));
        // sort
        SelectionSort.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
