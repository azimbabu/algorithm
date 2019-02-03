package dp;

import java.util.Arrays;

public class MinimumSubsetSumDifference {

    public int minSumDiffBruteForce(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        return minSumDiffBruteForceHelper(nums, 0, sum, 0);
    }

    private int minSumDiffBruteForceHelper(int[] nums, int sum, int remaining, int index) {
        if (index == nums.length) {
            return Math.abs(sum - remaining);
        } else {
            int withItem = minSumDiffBruteForceHelper(nums, sum + nums[index], remaining - nums[index], index + 1);
            int withoutItem = minSumDiffBruteForceHelper(nums, sum, remaining, index + 1);
            return Math.min(Math.abs(sum - remaining), Math.min(withItem, withoutItem));
        }
    }

    /**
     * Topdown DP
     * @param nums
     * @return
     */
    public int minSumDiffTopDownDP(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int[][] dp = new int[nums.length][1 + sum];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return minSumDiffTopDownDPHelper(nums, 0, sum, 0, dp);
    }

    private int minSumDiffTopDownDPHelper(int[] nums, int sum, int remaining, int index, int[][] dp) {
        if (index == nums.length) {
            return Math.abs(sum - remaining);
        } else if (dp[index][sum] == -1) {
            int withItem = minSumDiffTopDownDPHelper(nums, sum + nums[index], remaining - nums[index], index + 1, dp);
            int withoutItem = minSumDiffTopDownDPHelper(nums, sum, remaining, index + 1, dp);
            dp[index][sum] = Math.min(Math.abs(sum - remaining), Math.min(withItem, withoutItem));
        }
        return dp[index][sum];
    }

    public int minSumDiffBottomUpDP(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        boolean[][] dp = new boolean[nums.length][1 + totalSum/2];
        // populate the sum=0 columns, as we can always form '0' sum with an empty set
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        // with only one number, we can form a subset only when the required sum is equal to that number
        for (int sum = 1; sum <= totalSum/2; sum++) {
            dp[0][sum] = nums[0] == sum;
        }

        // process all subsets for all sums
        for(int i = 1; i < nums.length; i++) {
            for (int sum = 1; sum <= totalSum/2; sum++) {
                dp[i][sum] = dp[i-1][sum] || (nums[i] <= sum ? dp[i-1][sum - nums[i]] : false);
            }
        }

        // Find the largest index in the last row which is true
        for (int sum = totalSum/2; sum >= 0; sum--) {
            if (dp[nums.length-1][sum]) {
                return Math.abs(sum - (totalSum - sum));
            }
        }

        return totalSum;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSubsetSumDifference().minSumDiffBruteForce(new int[]{1, 2, 3, 9}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffBruteForce(new int[]{1, 2, 7, 1, 5}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffBruteForce(new int[]{1, 3, 100, 4}));

        System.out.println(new MinimumSubsetSumDifference().minSumDiffTopDownDP(new int[]{1, 2, 3, 9}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffTopDownDP(new int[]{1, 2, 7, 1, 5}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffTopDownDP(new int[]{1, 3, 100, 4}));

        System.out.println(new MinimumSubsetSumDifference().minSumDiffBottomUpDP(new int[]{1, 2, 3, 9}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffBottomUpDP(new int[]{1, 2, 7, 1, 5}));
        System.out.println(new MinimumSubsetSumDifference().minSumDiffBottomUpDP(new int[]{1, 3, 100, 4}));
    }
}
