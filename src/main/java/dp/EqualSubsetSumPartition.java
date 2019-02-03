package dp;

import java.util.Arrays;

public class EqualSubsetSumPartition {

    /**
     * Bruteforce
     * @param nums
     * @return
     */
    public boolean canPartitionRecursive(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // if 'sum' is a an odd number, we can't have two subsets with equal sum
        if (sum % 2 != 0) {
            return false;
        }

        return canPartitionRecursiveHelper(nums, sum / 2, 0);
    }

    private boolean canPartitionRecursiveHelper(int[] nums, int sum, int index) {
        // base check
        if (sum == 0) {
            return true;
        } else if (index == nums.length) {
            return false;
        }

        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        return (nums[index] <= sum ? canPartitionRecursiveHelper(nums, sum - nums[index], index + 1) : false) ||
               // recursive call after excluding the number at the currentIndex
               canPartitionRecursiveHelper(nums, sum, index + 1);
    }

    public boolean canPartitionTopDownDP(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) {
            // if 'sum' is a an odd number, we can't have two subsets with equal sum
            return false;
        }

        // initialize all values to a default value:
        // '-1' represents uninitialized,
        // '0' represents false,
        // and '1' represents true
        int[][] dp = new int[nums.length][sum/2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return canPartitionTopDownDPHelper(nums, sum/2, 0, dp);
    }

    private boolean canPartitionTopDownDPHelper(int[] nums, int sum, int index, int[][] dp) {
        if (sum == 0) {
            return true;
        } else if (index == nums.length) {
            return false;
        } else if (dp[index][sum] == -1) {
            boolean result = (nums[index] <= sum ? canPartitionTopDownDPHelper(nums, sum - nums[index], index+1, dp) : false) ||
                             canPartitionTopDownDPHelper(nums, sum, index+1, dp);
            dp[index][sum] = result ? 1 : 0;
        }
        return dp[index][sum] == 1;
    }

    public boolean canPartitionBottomUp(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        if (totalSum % 2 != 0) {
            // if 'sum' is a an odd number, we can't have two subsets with equal sum
            return false;
        }

        boolean[][] dp = new boolean[nums.length][1 + totalSum/2];
        for (int i = 0; i < nums.length; i++) {
            for (int sum = 0; sum <= totalSum/2; sum++) {
                boolean withItem;
                boolean withoutItem;
                if (i > 0) {
                    withItem = nums[i] <= sum ? dp[i-1][sum - nums[i]] : false;
                    withoutItem = dp[i-1][sum];
                } else {
                    withItem = nums[i] == sum;
                    withoutItem = false;
                }
                dp[i][sum] = withoutItem || withItem;
            }
        }
        return dp[nums.length-1][totalSum/2];
    }

    public static void main(String[] args) {
        EqualSubsetSumPartition partition = new EqualSubsetSumPartition();
        System.out.println(partition.canPartitionRecursive(new int[] {1, 2, 3, 4}));
        System.out.println(partition.canPartitionRecursive(new int[] {1, 1, 3, 4, 7}));
        System.out.println(partition.canPartitionRecursive(new int[] {2, 3, 4, 6}));

        System.out.println(partition.canPartitionTopDownDP(new int[] {1, 2, 3, 4}));
        System.out.println(partition.canPartitionTopDownDP(new int[] {1, 1, 3, 4, 7}));
        System.out.println(partition.canPartitionTopDownDP(new int[] {2, 3, 4, 6}));

        System.out.println(partition.canPartitionBottomUp(new int[] {1, 2, 3, 4}));
        System.out.println(partition.canPartitionBottomUp(new int[] {1, 2, 3, 4}));
        System.out.println(partition.canPartitionBottomUp(new int[] {1, 1, 3, 4, 7}));
        System.out.println(partition.canPartitionBottomUp(new int[] {2, 3, 4, 6}));
    }
}
