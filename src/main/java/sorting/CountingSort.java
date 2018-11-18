package sorting;

import java.util.Arrays;

public class CountingSort {

    public static void sort(int[] array) {
        int k = Integer.MIN_VALUE;
        for (int value : array) {
            k = Math.max(k, value);
        }

        int[] counts = new int[k+1];
        Arrays.fill(counts, 0);

        for (int value : array) {
            counts[value]++;    // counts[i] now contains the number of elements equal to i.
         }

        for (int i=1; i < counts.length; i++) {
            counts[i] += counts[i-1];   // counts[i] now contains the number of elements less than or equal to i.
        }

        int[] result = new int[array.length];
        for (int i = array.length-1; i >= 0; i--) {
            result[counts[array[i]]-1] = array[i];
            counts[array[i]]--;
        }

        for (int i = 0; i < result.length; i++) {
            array[i] = result[i];
        }
    }

    public static void main(String[] args) {
        testCase(new int[]{2, 5, 3, 0, 2, 3, 0, 3});
    }

    private static void testCase(int[] array) {
        System.out.println("Unsorted : " + Arrays.toString(array));
        CountingSort.sort(array);
        System.out.println("Sorted : " + Arrays.toString(array));
    }
}
