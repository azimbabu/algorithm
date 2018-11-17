package sorting;

import java.util.Arrays;
import java.util.Random;

import static util.Utils.swap;

public class RandomizedQuickSort {

    public static final Random RANDOM = new Random();

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
        int randomIndex = getRandom(left, right);
        swap(array, randomIndex, right);

        int key = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= key) {
                i = i + 1;
                swap(array, i, j);
            }
        }
        swap(array, i+1, right);
        return i+1;
    }

    private static int getRandom(int min, int max) {
        return min + RANDOM.nextInt(max - min + 1);
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
        RandomizedQuickSort.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
