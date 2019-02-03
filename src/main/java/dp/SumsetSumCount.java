package dp;

public class SumsetSumCount {

    /**
     * Bruteforce
     * @param nums
     * @param sum
     * @return
     */
    public int countSubsetsBruteForce(int[] nums, int sum) {
        return countSubsetsBruteForceHelper(nums, 0, sum);
    }

    private int countSubsetsBruteForceHelper(int[] nums, int index, int remaining) {
        if (remaining == 0) {
            return 1;
        } else if (remaining < 0 || index == nums.length) {
            return 0;
        } else {
            return countSubsetsBruteForceHelper(nums, index+1, remaining) +
                   countSubsetsBruteForceHelper(nums, index+1, remaining - nums[index]);
        }
    }

    /**
     * Topdown DP
     * @param nums
     * @param sum
     * @return
     */
    public int countSubsetsTopDownDP(int[] nums, int sum) {
        return countSubsetsTopDownDPHelper(nums, 0, sum, new Integer[nums.length][sum+1]);
    }

    private int countSubsetsTopDownDPHelper(int[] nums, int index, int remaining, Integer[][] dp) {
        if (remaining == 0) {
            return 1;
        } else if (remaining < 0 || index == nums.length) {
            return 0;
        } else if (dp[index][remaining] == null) {  // check if we have not already processed a similar problem
            dp[index][remaining] = countSubsetsTopDownDPHelper(nums, index+1, remaining, dp) +  // recursive call after excluding the number at the currentIndex
                   countSubsetsTopDownDPHelper(nums, index+1, remaining - nums[index], dp); // recursive call after choosing the number at the currentIndex
        }
        return dp[index][remaining];
    }

    public int countSubsetsBottomUpDP(int[] nums, int targetSum) {
        int[][] dp = new int[nums.length][targetSum+1];

        // populate the sum=0 columns, as we will always have an empty set for zero sum
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (int sum = 1; sum <= targetSum; sum++) {
            dp[0][sum] = nums[0] == sum ? 1 : 0;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int sum = 1; sum <= targetSum; sum++) {
                dp[i][sum] = dp[i-1][sum] + // exclude the number
                             (nums[i] <= sum ? dp[i-1][sum - nums[i]] : 0); // include the number, if it does not exceed the sum
            }
        }

        // the bottom-right corner will have our answer.
        return dp[nums.length-1][targetSum];
    }

    public int countSubsetsBottomUpDPOptimized(int[] nums, int targetSum) {
        int[] dp = new int[targetSum+1];

        dp[0] = 1;

        // with only one number, we can form a subset only when the required sum is equal to its value
        for (int sum = 1; sum <= targetSum; sum++) {
            dp[sum] = nums[0] == sum ? 1 : 0;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int sum = targetSum; sum >= 0; sum--) {
                dp[sum] = dp[sum] + // exclude the number
                             (nums[i] <= sum ? dp[sum - nums[i]] : 0); // include the number, if it does not exceed the sum
            }
        }

        // the bottom-right corner will have our answer.
        return dp[targetSum];

    }

    public static void main(String[] args) {
        System.out.println(new SumsetSumCount().countSubsetsBruteForce(new int[]{1, 1, 2, 3}, 4));  // 3
        System.out.println(new SumsetSumCount().countSubsetsBruteForce(new int[]{1, 2, 7, 1, 5}, 9));  // 3

        System.out.println(new SumsetSumCount().countSubsetsTopDownDP(new int[]{1, 1, 2, 3}, 4));  // 3
        System.out.println(new SumsetSumCount().countSubsetsTopDownDP(new int[]{1, 2, 7, 1, 5}, 9));  // 3

        System.out.println(new SumsetSumCount().countSubsetsBottomUpDP(new int[]{1, 1, 2, 3}, 4));  // 3
        System.out.println(new SumsetSumCount().countSubsetsBottomUpDP(new int[]{1, 2, 7, 1, 5}, 9));  // 3

        System.out.println(new SumsetSumCount().countSubsetsBottomUpDPOptimized(new int[]{1, 1, 2, 3}, 4));  // 3
        System.out.println(new SumsetSumCount().countSubsetsBottomUpDPOptimized(new int[]{1, 2, 7, 1, 5}, 9));  // 3
    }
}
