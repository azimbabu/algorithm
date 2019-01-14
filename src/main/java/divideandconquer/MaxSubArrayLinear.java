package divideandconquer;

import divideandconquer.MaxSubArrayDivideAndConquer.MaxSubArrayResult;

import java.util.Arrays;

public class MaxSubArrayLinear {

    public MaxSubArrayResult findMaximumSubarray(int[] array) {
        int maxStart = -1;
        int maxEnd = -1;
        int maxSum = Integer.MIN_VALUE;

        for (int left = 0, right = 0, sum = 0; right < array.length; right++) {
            if(sum > 0) {   // sum + array[right] > array[right]
                sum += array[right];
            } else {
                sum = array[right];
                left = right;
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxStart = left;
                maxEnd = right;
            }
        }

        return new MaxSubArrayResult(maxStart, maxEnd, maxSum);
    }

    public static void main(String[] args) {
        testCase(new int[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7}, new MaxSubArrayResult(7, 10, 43));
    }

    private static void testCase(int[] array, MaxSubArrayResult expected) {
        MaxSubArrayResult actual = new MaxSubArrayLinear().findMaximumSubarray(array);
        System.out.println(String.format("Array=%s, SubArray=%s", Arrays.toString(array), actual));
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
