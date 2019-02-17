package dp;

import java.util.Arrays;

public class NumberFactors {

    /**
     * Bruteforce
     * @param n
     * @return
     */
    public int countWaysBruteforce(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else {
            return countWaysBruteforce(n - 1) + countWaysBruteforce(n - 3) + countWaysBruteforce(n - 4);
        }
    }

    /**
     * Topdown DP
     * @param n
     * @return
     */
    public int countWaysTopDownDP(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return countWaysTopDownDPHelper(n, dp);
    }

    private int countWaysTopDownDPHelper(int n, int[] dp) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        } else if (dp[n] == -1) {
            dp[n] = countWaysTopDownDPHelper(n - 1, dp) + countWaysTopDownDPHelper(n - 3, dp) + countWaysTopDownDPHelper(n - 4, dp);
        }
        return dp[n];
    }

    /**
     * Bottom up DP
     * @param n
     * @return
     */
    public int countWaysBottomUpDP(int n) {
        int[] dp = new int[n+1];
        for (int i=0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1;
            } else {
                int subtract1 = i-1 >= 0 ? dp[i-1] : 0;
                int subtract3 = i-3 >= 0 ? dp[i-3] : 0;
                int subtract4 = i-4 >= 0 ? dp[i-4] : 0;
                dp[i] = subtract1 + subtract3 + subtract4;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new NumberFactors().countWaysBruteforce(4));
        System.out.println(new NumberFactors().countWaysBruteforce(5));
        System.out.println(new NumberFactors().countWaysBruteforce(6));

        System.out.println(new NumberFactors().countWaysTopDownDP(4));
        System.out.println(new NumberFactors().countWaysTopDownDP(5));
        System.out.println(new NumberFactors().countWaysTopDownDP(6));

        System.out.println(new NumberFactors().countWaysBottomUpDP(4));
        System.out.println(new NumberFactors().countWaysBottomUpDP(5));
        System.out.println(new NumberFactors().countWaysBottomUpDP(6));
    }
}
