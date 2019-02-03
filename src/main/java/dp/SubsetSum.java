package dp;

import java.util.Arrays;

public class SubsetSum {

    /**
     * Topdown DP
     * @param nums
     * @param sum
     * @return
     */
    public boolean canPartitionTopDownDP(int[] nums, int sum) {
        // -1 : uninitialized, 0 : false, 1: true
        int[][] dp = new int[nums.length][1+sum];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return canPartitionTopDownDPHelper(nums, 0, sum, dp);
    }

    private boolean canPartitionTopDownDPHelper(int[] nums, int index, int sum, int[][] dp) {
        if (sum == 0) {
            return true;
        } else if (sum < 0 || index == nums.length) {
            return false;
        } else if (dp[index][sum] == -1) {
            boolean withItem = nums[index] <= sum ? canPartitionTopDownDPHelper(nums, index + 1, sum - nums[index], dp) : false;
            boolean withoutItem = canPartitionTopDownDPHelper(nums, index + 1, sum, dp);
            dp[index][sum] = (withItem || withoutItem) ? 1 : 0;
        }
        return dp[index][sum] == 1;
    }

    public boolean canPartitionBottomUpDP(int[] nums, int targetSum) {
        boolean[][] dp = new boolean[nums.length][1+targetSum];
        for (int i = 0; i < nums.length; i++) {
            for (int sum = 0; sum <= targetSum; sum++) {
                if (sum == 0) {
                    dp[i][sum] = true;  // empty set will have sum 0
                    continue;
                }

                boolean withItem, withoutItem;
                if (i == 0) {
                    withItem = nums[i] == sum;
                    withoutItem = false;
                } else {
                    withItem = nums[i] <= sum ? dp[i-1][sum - nums[i]] : false;
                    withoutItem = dp[i-1][sum];
                }
                dp[i][sum] = withItem || withoutItem;
            }
        }
        return dp[nums.length-1][targetSum];
    }

    public boolean canPartitionBottomUpDPOptimized(int[] nums, int targetSum) {
        boolean[] dp = new boolean[1+targetSum];
        for (int i = 0; i < nums.length; i++) {
            for (int sum = targetSum; sum >= 0; sum--) {
                if (sum == 0) {
                    dp[sum] = true;  // empty set will have sum 0
                    continue;
                }

                boolean withItem, withoutItem;
                if (i == 0) {
                    withItem = nums[i] == sum;
                    withoutItem = false;
                } else {
                    withItem = nums[i] <= sum ? dp[sum - nums[i]] : false;
                    withoutItem = dp[sum];
                }
                dp[sum] = withItem || withoutItem;
            }
        }
        return dp[targetSum];
    }

    public static void main(String[] args) {
        testCaseTopDownDP(new int[]{1, 2, 3, 7}, 6, true);
        testCaseTopDownDP(new int[]{1, 2, 7, 1, 5}, 10, true);
        testCaseTopDownDP(new int[]{1, 3, 4, 8}, 6, false);

        testCaseBottomUpDP(new int[]{1, 2, 3, 7}, 6, true);
        testCaseBottomUpDP(new int[]{1, 2, 7, 1, 5}, 10, true);
        testCaseBottomUpDP(new int[]{1, 3, 4, 8}, 6, false);

        testCaseBottomUpDPOptimized(new int[]{1, 2, 3, 7}, 6, true);
        testCaseBottomUpDPOptimized(new int[]{1, 2, 7, 1, 5}, 10, true);
        testCaseBottomUpDPOptimized(new int[]{1, 3, 4, 8}, 6, false);
    }

    private static void testCaseTopDownDP(int[] nums, int sum, boolean expected) {
        boolean actual = new SubsetSum().canPartitionTopDownDP(nums, sum);
        System.out.println(String.format("Nums=%s, Partition=%s", Arrays.toString(nums), actual));
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(int[] nums, int sum, boolean expected) {
        boolean actual = new SubsetSum().canPartitionBottomUpDP(nums, sum);
        System.out.println(String.format("Nums=%s, Partition=%s", Arrays.toString(nums), actual));
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDPOptimized(int[] nums, int sum, boolean expected) {
        boolean actual = new SubsetSum().canPartitionBottomUpDPOptimized(nums, sum);
        System.out.println(String.format("Nums=%s, Partition=%s", Arrays.toString(nums), actual));
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
