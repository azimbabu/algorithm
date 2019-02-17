package dp;

import java.util.Arrays;

public class StairCaseMinimumFee {

    /**
     * Bruteforce
     * @param fees
     * @return
     */
    public int findMinFeeBruteforce(int[] fees) {
        return findMinFeeBruteforceHelper(0, fees);
    }

    private int findMinFeeBruteforceHelper(int current, int[] fees) {
        if (current > fees.length - 1) {
            return 0;
        } else {
            int minNextFee = Integer.MAX_VALUE;
            for (int step=1; step <=3; step++) {
                minNextFee = Math.min(minNextFee, findMinFeeBruteforceHelper(current + step, fees));
            }
            return minNextFee != Integer.MAX_VALUE ? fees[current] + minNextFee : Integer.MAX_VALUE;
        }
    }

    /**
     * Topdown DP
     * @param fees
     * @return
     */
    public int findMinFeeTopDownDP(int[] fees) {
        int[] dp = new int[fees.length];
        Arrays.fill(dp, -1);

        return findMinFeeTopDownDPHelper(0, fees, dp);
    }

    private int findMinFeeTopDownDPHelper(int current, int[] fees, int[] dp) {
        if (current > fees.length-1) {
            return 0;
        } else if (dp[current] == -1) {
            int minNextFee = Integer.MAX_VALUE;
            for (int step=1; step <=3; step++) {
                minNextFee = Math.min(minNextFee, findMinFeeTopDownDPHelper(current + step, fees, dp));
            }
            dp[current] = minNextFee != Integer.MAX_VALUE ? fees[current] + minNextFee : Integer.MAX_VALUE;
        }
        return dp[current];
    }

    public int findMinFeeBottomUpDP(int[] fees) {
        int[] dp = new int[fees.length];
        dp[fees.length-1] = fees[fees.length-1];

        for (int current = fees.length-2; current >= 0; current--) {
            int minNextFee = Integer.MAX_VALUE;
            for (int step=1; step <=3; step++) {
                minNextFee = Math.min(minNextFee, (current + step) < fees.length ? dp[current+step] : 0);
            }
            dp[current] = minNextFee != Integer.MAX_VALUE ? fees[current] + minNextFee : Integer.MAX_VALUE;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        testCaseBruteforce(new int[]{1,2,5,2,1,2}, 3);
        testCaseBruteforce(new int[]{2,3,4,5}, 5);
        testCaseBruteforce(new int[]{9,2,5,2,1,2}, 11);

        testCaseTopDownDP(new int[]{1,2,5,2,1,2}, 3);
        testCaseTopDownDP(new int[]{2,3,4,5}, 5);
        testCaseTopDownDP(new int[]{9,2,5,2,1,2}, 11);

        testCaseBottomUpDP(new int[]{1,2,5,2,1,2}, 3);
        testCaseBottomUpDP(new int[]{2,3,4,5}, 5);
        testCaseBottomUpDP(new int[]{9,2,5,2,1,2}, 11);
    }

    private static void testCaseBruteforce(int[] fees, int expected) {
        int actual = new StairCaseMinimumFee().findMinFeeBruteforce(fees);
        System.out.println(String.format("Fees=%s, Min Fee=%s", Arrays.toString(fees), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseTopDownDP(int[] fees, int expected) {
        int actual = new StairCaseMinimumFee().findMinFeeTopDownDP(fees);
        System.out.println(String.format("Fees=%s, Min Fee=%s", Arrays.toString(fees), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(int[] fees, int expected) {
        int actual = new StairCaseMinimumFee().findMinFeeBottomUpDP(fees);
        System.out.println(String.format("Fees=%s, Min Fee=%s", Arrays.toString(fees), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
