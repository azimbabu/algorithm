package sorting;

import java.util.Arrays;

import static util.Utils.swap;

public class QuickSort {

    public static void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);
            sort(array, left, partitionIndex-1);
            sort(array, partitionIndex+1, right);
        }
    }

    private static int partition(int[] array, int left, int right) {
        int x = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= x) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        swap(array, i+1, right);
        return i+1;
    }

    public static void main(String[] args) {
        testCase(new int[] {5, 2, 4, 6, 1 ,3});
        testCase(new int[] {31, 41, 59, 26, 41, 58});
        testCase(new int[] {5, 2, 4, 7, 1 ,3, 2, 6});
        testCase(new int[] {2, 8, 7, 1, 3, 5, 6, 4});
    }

    private static void testCase(int[] array) {
        System.out.println("Unsorted=" + Arrays.toString(array));
        // sort
        QuickSort.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
