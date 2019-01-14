package divideandconquer;

import divideandconquer.MaxSubArrayDivideAndConquer.MaxSubArrayResult;

import java.util.Arrays;

public class MaxSubArrayBruteForce {

    /**
     * Bruteforce : O(n^2)
     * @param array
     * @return
     */
    public MaxSubArrayResult findMaximumSubarray(int[] array) {
        int start = -1, end = -1, maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }

        return new MaxSubArrayResult(start, end, maxSum);
    }

    /**
     * Bruteforce : O(n^3)
     * @param array
     * @return
     */
    public MaxSubArrayResult findMaximumSubarray2(int[] array) {
        int n = array.length;
        int maxSum = Integer.MIN_VALUE;
        int start = -1;
        int end = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += array[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }

        return new MaxSubArrayResult(start, end, maxSum);
    }

    public static void main(String[] args) {
        testCase(new int[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7}, new MaxSubArrayResult(7, 10, 43));
    }

    private static void testCase(int[] array, MaxSubArrayResult expected) {
        MaxSubArrayResult actual = new MaxSubArrayBruteForce().findMaximumSubarray(array);
        System.out.println(String.format("Array=%s, SubArray=%s", Arrays.toString(array), actual));
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
