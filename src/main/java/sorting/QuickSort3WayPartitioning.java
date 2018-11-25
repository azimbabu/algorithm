package sorting;

import java.util.Arrays;
import java.util.Random;

import static util.Utils.getRandom;
import static util.Utils.swap;

public class QuickSort3WayPartitioning {

    public static final Random RANDOM = new Random();

    public static void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            int randomIndex = getRandom(RANDOM, left, right);
            swap(array, randomIndex, left);

            int lt = left, gt = right, i = left+1;
            int key = array[lt];

            while (i <= gt) {
                // loop invariant :
                // array[left..lt-1] < key ,
                // array[lt..i-1] == key
                // array[i..gt] not yet examined
                // array[gt+1..right] > key
                int compare = Integer.compare(array[i], key);
                if (compare < 0) {
                    swap(array, lt++, i++);
                } else if (compare > 0) {
                    swap(array, gt--, i);
                } else {
                    i++;
                }
            }

            sort(array, left, lt-1);
            sort(array, gt+1, right);
        }
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
        QuickSort3WayPartitioning.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
