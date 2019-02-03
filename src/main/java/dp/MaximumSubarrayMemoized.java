package dp;

import java.util.Arrays;

public class MaximumSubarrayMemoized {

    /**
     * Recursive substructure : dp[i] = dp[i-1] > 0 ? dp[i-1] + array[i] : array[i]
     * Here dp[i] is maximum subarray sum that ends at index i.
     * Time complexity : O(n)
     * Space complexity : O(n)
     * @param array
     * @return
     */
    public int maxSubArray(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int maxSum = dp[0];

        for (int i=1; i < array.length; i++) {
            dp[i] = dp[i-1] > 0 ? dp[i-1] + array[i] : array[i];
            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }

    /**
     * Recursive substructure : dp[i] = dp[i-1] > 0 ? dp[i-1] + array[i] : array[i]
     * Here dp[i] is maximum subarray sum that ends at index i.
     * Time complexity : O(n)
     * Space complexity : O(1)
     * @param array
     * @return
     */
    public int maxSubArray2(int[] array) {
        int maxSumEndsHere = array[0];
        int maxSum = maxSumEndsHere;

        for (int i=1; i < array.length; i++) {
            maxSumEndsHere = maxSumEndsHere > 0 ? maxSumEndsHere + array[i] : array[i];
            maxSum = Math.max(maxSum, maxSumEndsHere);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        testCase(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6);
        testCase2(new int[]{-2,1,-3,4,-1,2,1,-5,4}, 6);
    }

    private static void testCase(int[] array, int expected) {
        int actual = new MaximumSubarrayMemoized().maxSubArray(array);
        System.out.println(String.format("Array=%s, MaxSubArraySum=%s", Arrays.toString(array), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual = %s", expected, actual));
        }
    }

    private static void testCase2(int[] array, int expected) {
        int actual = new MaximumSubarrayMemoized().maxSubArray2(array);
        System.out.println(String.format("Array=%s, MaxSubArraySum=%s", Arrays.toString(array), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual = %s", expected, actual));
        }
    }
}
