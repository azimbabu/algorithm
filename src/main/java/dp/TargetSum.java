package dp;

import java.util.Arrays;

public class TargetSum {

    /**
     * Top down dp
     * @param nums
     * @param targetSum
     * @return
     */
    public int findTargetSumWaysTopDownDP(int[] nums, int targetSum) {
        int totalSum = sum(nums);
        if (targetSum > totalSum || (totalSum + targetSum) % 2 == 1) {
            return 0;
        }

        int newTarget = (totalSum + targetSum)/2;

        int[][] dp = new int[nums.length][1+newTarget];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return findTargetSumWaysHelper(nums, 0, newTarget, dp);
    }

    private int sum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    private int findTargetSumWaysHelper(int[] nums, int index, int targetSum, int[][] dp) {
        if (index == nums.length) {
            return targetSum == 0 ? 1 : 0;
        } else if (dp[index][targetSum] == -1) {
            dp[index][targetSum] = findTargetSumWaysHelper(nums, index+1, targetSum, dp) +
                                   (targetSum >= nums[index] ? findTargetSumWaysHelper(nums, index+1, targetSum - nums[index], dp) : 0);
        }
        return dp[index][targetSum];
    }

    /**
     * Bottom up dp
     * @param nums
     * @param targetSum
     * @return
     */
    public int findTargetSumWaysBottomUpDP(int[] nums, int targetSum) {
        int totalSum = sum(nums);
        if (targetSum > totalSum || (targetSum + totalSum) % 2 == 1) {
            return 0;
        }

        int newTarget = (targetSum + totalSum)/2;
        int[][] dp = new int[nums.length][newTarget+1];

        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }

        for (int sum = 1; sum <= newTarget; sum++) {
            dp[0][sum] = (nums[0] == sum ? 1 : 0);
        }

        for (int i = 1; i < nums.length; i++) {
            for (int sum = 1; sum <= newTarget; sum++) {
                dp[i][sum] = dp[i-1][sum] + (sum >= nums[i] ? dp[i-1][sum - nums[i]] : 0);
            }
        }
        return dp[nums.length-1][newTarget];
    }

    public static void main(String[] args) {
        testCase(new int[]{1, 1, 1, 1, 1}, 3, 5);
        testCase(new int[]{1, 1, 2, 3}, 1, 3);
        testCase(new int[]{1, 2, 7, 1}, 9, 2);
        testCase(new int[]{1}, 2, 0);
        testCase(new int[]{1, 2, 1}, 0, 2);

        testCase2(new int[]{1, 1, 1, 1, 1}, 3, 5);
        testCase2(new int[]{1, 1, 2, 3}, 1, 3);
        testCase2(new int[]{1, 2, 7, 1}, 9, 2);
        testCase2(new int[]{1}, 2, 0);
        testCase2(new int[]{1, 2, 1}, 0, 2);
    }

    private static void testCase(int[] nums, int target, int expected) {
        int actual = new TargetSum().findTargetSumWaysTopDownDP(nums, target);
        System.out.println(String.format("Nums=%s, Target=%s, Ans=%s", Arrays.toString(nums), target, actual));
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCase2(int[] nums, int target, int expected) {
        int actual = new TargetSum().findTargetSumWaysBottomUpDP(nums, target);
        System.out.println(String.format("Nums=%s, Target=%s, Ans=%s", Arrays.toString(nums), target, actual));
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
