package divideandconquer;

import java.util.Arrays;

public class CountInversion {

    public static int countInversion(int[] array) {
        return sortAndCount(array, 0, array.length-1);
    }

    private static int sortAndCount(int[] array, int left, int right) {
        int n = right - left + 1;
        if (n == 1) {
            return 0;
        } else {
            int mid = left + (right - left)/2;
            int leftCount = sortAndCount(array, left, mid);
            int rightCount = sortAndCount(array, mid+1, right);
            int splitCount = mergeAndCountSplit(array, left, mid, right);
            return leftCount + rightCount + splitCount;
        }
    }

    private static int mergeAndCountSplit(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int numInversions = 0;
        for (int i = 0, j = 0, k = left; k <= right; k++) {
            if (j == n2) {
                array[k] = leftArray[i++];
            } else if (i == n1) {
                array[k] = rightArray[j++];
            } else if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i++];
            } else {
                array[k] = rightArray[j++];
                numInversions += n1 - i;
            }
        }

        return numInversions;
    }

    public static void main(String[] args) {
        testCase(new int[]{1, 3, 5, 2, 4, 6});  // expected 3
        testCase(new int[]{1, 2, 3, 4, 5, 6});  // expected 0
        testCase(new int[]{6, 5, 4, 3, 2, 1});  // expected 15
        testCase(new int[]{54044, 14108, 79294, 29649, 25260, 60660, 2995, 53777, 49689, 9083});    // expected 28
    }

    private static void testCase(int[] array) {
        System.out.println(String.format("Count inversion of %s = %d", Arrays.toString(array), CountInversion.countInversion(array)));
    }
}
