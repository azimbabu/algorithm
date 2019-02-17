package dp;

import java.util.Arrays;

public class MinJumps {

    /**
     * Bruteforce
     * @param nums
     * @return
     */
    public int countMinJumpsBruteforce(int[] nums) {
        return countMinJumpsBruteforceHelper(0, nums);
    }

    private int countMinJumpsBruteforceHelper(int current, int[] nums) {
        if (current == nums.length-1) {
            return 0;
        } else {
            int furthest = Math.min(current + nums[current], nums.length-1);
            int minNextJumps = Integer.MAX_VALUE;
            for (int next = current+1; next <= furthest; next++) {
                minNextJumps = Math.min(minNextJumps, countMinJumpsBruteforceHelper(next, nums));
            }
            return minNextJumps != Integer.MAX_VALUE ? 1 + minNextJumps : Integer.MAX_VALUE;
        }
    }

    /**
     * Topdown DP
     * @param nums
     * @return
     */
    public int countMinJumpsTopDownDP(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);

        return countMinJumpsTopDownDPHelper(0, nums, dp);
    }

    private int countMinJumpsTopDownDPHelper(int current, int[] nums, int[] dp) {
        if (current == nums.length-1) {
            return 0;
        } else if (dp[current] == -1) {
            int furthest = Math.min(current + nums[current], nums.length-1);
            int minNextJumps = Integer.MAX_VALUE;
            for (int next = current+1; next <= furthest; next++) {
                minNextJumps = Math.min(minNextJumps, countMinJumpsTopDownDPHelper(next, nums, dp));
            }
            dp[current] = minNextJumps != Integer.MAX_VALUE ? 1 + minNextJumps : Integer.MAX_VALUE;
        }
        return dp[current];
    }

    public int countMinJumpsBottomUpDP(int[] nums) {
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 0;

        for (int current = nums.length-2; current >= 0; current--) {
            int furthest = Math.min(current + nums[current], nums.length-1);
            int minNextJumps = Integer.MAX_VALUE;
            for (int next = current+1; next <= furthest; next++) {
                minNextJumps = Math.min(minNextJumps, dp[next]);
            }
            dp[current] = minNextJumps != Integer.MAX_VALUE ? 1 + minNextJumps : Integer.MAX_VALUE;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        testCaseBruteForce(new int[]{2,1,1,1,4}, 3);
        testCaseBruteForce(new int[]{1,1,3,6,9,3,0,1,3}, 4);

        testCaseTopDownDP(new int[]{2,1,1,1,4}, 3);
        testCaseTopDownDP(new int[]{1,1,3,6,9,3,0,1,3}, 4);

        testCaseBottomUpDP(new int[]{2,1,1,1,4}, 3);
        testCaseBottomUpDP(new int[]{1,1,3,6,9,3,0,1,3}, 4);
    }

    private static void testCaseBruteForce(int[] nums, int expected) {
        int actual = new MinJumps().countMinJumpsBruteforce(nums);
        System.out.println(String.format("Nums=%s, Min Jumps=%s", Arrays.toString(nums), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseTopDownDP(int[] nums, int expected) {
        int actual = new MinJumps().countMinJumpsTopDownDP(nums);
        System.out.println(String.format("Nums=%s, Min Jumps=%s", Arrays.toString(nums), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(int[] nums, int expected) {
        int actual = new MinJumps().countMinJumpsBottomUpDP(nums);
        System.out.println(String.format("Nums=%s, Min Jumps=%s", Arrays.toString(nums), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
