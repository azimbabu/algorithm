package sorting;

import java.util.Arrays;

public class MergeSortWithoutSentinels {

    public static void sort(int[] array) {
        sort(array, 0, array.length-1);
    }

    private static void sort(int[] array, int p, int r) {
        if (p < r) {
            int q = p + (r - p) / 2;
            sort(array, p, q);
            sort(array, q+1, r);
            merge(array, p, q, r);
        }
    }

    /**
     * Merge the sorted subarrays array[p .. q] and array[q+1 .. r] to form a single sorted subarray array[p .. r].
     * p <= q < r
     * @param array
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] array, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[p + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[q + 1 + j];
        }

        for (int i = 0, j = 0, k = p; k <= r; k++) {
            if (i == n1) {
                array[k] = rightArray[j++];
            } else if (j == n2) {
                array[k] = leftArray[i++];
            } else if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i++];
            } else {
                array[k] = rightArray[j++];
            }
        }
    }

    public static void main(String[] args) {
        testCase(new int[] {5, 2, 4, 6, 1 ,3});
        testCase(new int[] {31, 41, 59, 26, 41, 58});
        testCase(new int[] {5, 2, 4, 7, 1 ,3, 2, 6});
    }

    private static void testCase(int[] array) {
        System.out.println("Unsorted=" + Arrays.toString(array));
        // sort
        MergeSortWithoutSentinels.sort(array);
        System.out.println(String.format("Sorted=%s", Arrays.toString(array)));
    }
}
