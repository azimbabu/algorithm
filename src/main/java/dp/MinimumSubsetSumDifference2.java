package dp;

import java.util.Arrays;

public class MinimumSubsetSumDifference2 {

    /**
     * Bruteforce
     * @param nums
     * @return
     */
    public int minSumBruteForce(int[] nums) {
        return minSumBruteForceHelper(nums, 0, 0, 0);
    }

    private int minSumBruteForceHelper(int[] nums, int index, int sum1, int sum2) {
        if (index == nums.length) {
            return Math.abs(sum1 - sum2);
        } else {
            // recursive call after including the number at the currentIndex in the first set
            int diff1 = minSumBruteForceHelper(nums, index+1, sum1 + nums[index], sum2);
            // recursive call after including the number at the currentIndex in the second set
            int diff2 = minSumBruteForceHelper(nums, index+1, sum1, sum2 + nums[index]);
            return Math.min(diff1, diff2);
        }
    }

    /**
     * Topdown DP
     * @param nums
     * @return
     */
    public int minSumTopDownDP(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        Integer[][] dp = new Integer[nums.length][1+sum];
        return minSumTopDownDPHelper(nums, 0, 0, 0, dp);
    }

    private int minSumTopDownDPHelper(int[] nums, int index, int sum1, int sum2, Integer[][] dp) {
        if (index == nums.length) {
            // base check
            return Math.abs(sum1 - sum2);
        } else if (dp[index][sum1] == null) {   // check if we have not already processed similar problem
            // recursive call after including the number at the currentIndex in the first set
            int diff1 = minSumTopDownDPHelper(nums, index+1, sum1 + nums[index], sum2, dp);

            // recursive call after including the number at the currentIndex in the second set
            int diff2 = minSumTopDownDPHelper(nums, index+1, sum1, sum2 + nums[index], dp);

            dp[index][sum1] = Math.min(diff1, diff2);
        }
        return dp[index][sum1];
    }

    public static void main(String[] args) {
        MinimumSubsetSumDifference2 sumDifference2 = new MinimumSubsetSumDifference2();
        System.out.println(sumDifference2.minSumBruteForce(new int[]{1, 2, 3, 9})); // 3
        System.out.println(sumDifference2.minSumBruteForce(new int[]{1, 2, 7, 1, 5})); // 0
        System.out.println(sumDifference2.minSumBruteForce(new int[]{1, 3, 100, 4})); // 92

        System.out.println(sumDifference2.minSumTopDownDP(new int[]{1, 2, 3, 9})); // 3
        System.out.println(sumDifference2.minSumTopDownDP(new int[]{1, 2, 7, 1, 5})); // 0
        System.out.println(sumDifference2.minSumTopDownDP(new int[]{1, 3, 100, 4})); // 92
    }
}
