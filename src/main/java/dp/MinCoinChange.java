package dp;

import java.util.Arrays;

public class MinCoinChange {

    /**
     * Brute force
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeBruteForce(int[] denominations, int total) {
        int count = countChangeBruteForceHelper(denominations, 0, total);
        return count != Integer.MAX_VALUE ? count : -1;
    }

    private int countChangeBruteForceHelper(int[] denominations, int index, int total) {
        if (index == denominations.length) {
            return total == 0 ? 0 : Integer.MAX_VALUE;
        } else if (total < 0) {
            return Integer.MAX_VALUE;
        } else {
            int withoutCoin = countChangeBruteForceHelper(denominations, index+1, total);
            int withCoin = total >= denominations[index] ? countChangeBruteForceHelper(denominations, index, total - denominations[index]) : Integer.MAX_VALUE;
            withCoin = withCoin != Integer.MAX_VALUE ? withCoin+1 : withCoin;
            return Math.min(withCoin, withoutCoin);
        }
    }

    /**
     * Top Down DP
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeTopDownDP(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][1+total];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int count = countChangeTopDownDPHelper(denominations, 0, total, dp);
        return count != Integer.MAX_VALUE ? count : -1;
    }

    private int countChangeTopDownDPHelper(int[] denominations, int index, int total, int[][] dp) {
        if (index == denominations.length) {
            return total == 0 ? 0 : Integer.MAX_VALUE;
        } else if (total < 0) {
            return Integer.MAX_VALUE;
        } else if (dp[index][total] == -1){
            int withoutCoin = countChangeTopDownDPHelper(denominations, index+1, total, dp);
            int withCoin = total >= denominations[index] ? countChangeTopDownDPHelper(denominations, index, total - denominations[index], dp) : Integer.MAX_VALUE;
            withCoin = withCoin != Integer.MAX_VALUE ? withCoin+1 : withCoin;
            dp[index][total] = Math.min(withCoin, withoutCoin);
        }
        return dp[index][total];
    }

    /**
     * Bottom up
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeBottomUpDP(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][1+total];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < denominations.length; i++) {
            for (int amount = 0; amount <= total; amount++) {
                if (amount == 0) {
                    dp[i][amount] = 0;
                } else {
                    int withoutCoin = i > 0 ? dp[i - 1][amount] : Integer.MAX_VALUE;
                    int withCoin = amount >= denominations[i] ? dp[i][amount - denominations[i]] : Integer.MAX_VALUE;
                    withCoin = withCoin != Integer.MAX_VALUE ? withCoin+1 : withCoin;
                    dp[i][amount] = Math.min(withCoin, withoutCoin);
                }
            }
        }

        return dp[denominations.length-1][total] != Integer.MAX_VALUE ? dp[denominations.length-1][total] : -1;
    }

    /**
     * Bottom up optimized
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeBottomUpDPOptimized(int[] denominations, int total) {
        int[] dp = new int[1+total];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < denominations.length; i++) {
            for (int amount = 0; amount <= total; amount++) {
                if (amount == 0) {
                    dp[amount] = 0;
                } else {
                    int withoutCoin = i > 0 ? dp[amount] : Integer.MAX_VALUE;
                    int withCoin = amount >= denominations[i] ? dp[amount - denominations[i]] : Integer.MAX_VALUE;
                    withCoin = withCoin != Integer.MAX_VALUE ? withCoin+1 : withCoin;
                    dp[amount] = Math.min(withCoin, withoutCoin);
                }
            }
        }

        return dp[total] != Integer.MAX_VALUE ? dp[total] : -1;
    }

    public static void main(String[] args) {
        MinCoinChange coinChange = new MinCoinChange();
        System.out.println(coinChange.countChangeBruteForce(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeBruteForce(new int[]{1, 2, 3}, 11));
        System.out.println(coinChange.countChangeBruteForce(new int[]{1, 2, 3}, 7));
        System.out.println(coinChange.countChangeBruteForce(new int[]{3, 5}, 7));

        System.out.println(coinChange.countChangeTopDownDP(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeTopDownDP(new int[]{1, 2, 3}, 11));
        System.out.println(coinChange.countChangeTopDownDP(new int[]{1, 2, 3}, 7));
        System.out.println(coinChange.countChangeTopDownDP(new int[]{3, 5}, 7));

        System.out.println(coinChange.countChangeBottomUpDP(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeBottomUpDP(new int[]{1, 2, 3}, 11));
        System.out.println(coinChange.countChangeBottomUpDP(new int[]{1, 2, 3}, 7));
        System.out.println(coinChange.countChangeBottomUpDP(new int[]{3, 5}, 7));

        System.out.println(coinChange.countChangeBottomUpDPOptimized(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeBottomUpDPOptimized(new int[]{1, 2, 3}, 11));
        System.out.println(coinChange.countChangeBottomUpDPOptimized(new int[]{1, 2, 3}, 7));
        System.out.println(coinChange.countChangeBottomUpDPOptimized(new int[]{3, 5}, 7));
    }
}
