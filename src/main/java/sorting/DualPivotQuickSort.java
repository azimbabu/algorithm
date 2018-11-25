package sorting;

import util.Utils;

import java.util.Arrays;
import java.util.Random;

import static util.Utils.getRandom;
import static util.Utils.swap;

public class DualPivotQuickSort {

    public static final Random RANDOM = new Random();

    public static void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            int[] pivots = partition(array, left, right);
            sort(array, left, pivots[0]-1);
            sort(array, pivots[0] + 1, pivots[1]-1);
            sort(array, pivots[1] + 1, right);
        }
    }

    private static int[] partition(int[] array, int left, int right) {
        int randomIndex1 = getRandom(RANDOM, left, right);
        swap(array, randomIndex1, left);

        int randomIndex2 = getRandom(RANDOM, left+1, right);
        swap(array, randomIndex2, right);

        if (array[left] > array[right]) {
            swap(array, left, right);
        }

        int lt = left + 1, gt = right-1;
        int i = left + 1;
        while (i <= gt) {
            if (array[i] < array[left]) {
                swap(array, lt++, i++);
            } else if (array[i] > array[right]) {
                swap(array, gt--, i);
            } else  {
                i++;
            }
        }

        swap(array, left, lt-1);
        swap(array, right, gt+1);

        return new int[] {lt-1, gt+1};
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
        DualPivotQuickSort.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
