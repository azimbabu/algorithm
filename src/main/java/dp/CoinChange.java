package dp;

import java.util.Arrays;

public class CoinChange {

    /**
     * Brute force
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeBruteforce(int[] denominations, int total) {
        return countChangeBruteforceHelper(denominations, 0, total);
    }

    private int countChangeBruteforceHelper(int[] denominations, int index, int total) {
        if (index == denominations.length) {
            return total == 0 ? 1 : 0;
        } else {
            int withCoin = total >= denominations[index] ?
                           countChangeBruteforceHelper(denominations, index, total - denominations[index]) :
                           0;
            int withoutCoin = countChangeBruteforceHelper(denominations, index+1, total);
            return withCoin + withoutCoin;
        }
    }

    /**
     *
     * @param denominations
     * @param total
     * @return
     */
    public int countChangeTopDownDP(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][1+total];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return countChangeTopDownDPHelper(denominations, 0, total, dp);
    }

    private int countChangeTopDownDPHelper(int[] denominations, int index, int total, int[][] dp) {
        if (index == denominations.length) {
            return total == 0 ? 1 : 0;
        } else if (dp[index][total] == -1) {
            int withCoin = total >= denominations[index] ?
                           countChangeTopDownDPHelper(denominations, index, total - denominations[index], dp) :
                           0;
            int withoutCoin = countChangeTopDownDPHelper(denominations, index+1, total, dp);
            dp[index][total] = withCoin + withoutCoin;
        }
        return dp[index][total];
    }

    public int countChangeBottomUpDP(int[] denominations, int total) {
        int[][] dp = new int[denominations.length][1+total];
        for (int i = 0; i < denominations.length; i++) {
            for (int amount = 0; amount <= total; amount++) {
                if (amount == 0) {
                    dp[i][amount] = 1;
                } else {
                    int withCoin = amount >= denominations[i] ?
                                   dp[i][amount-denominations[i]] :
                                   0;
                    int withoutCoin = i > 0 ? dp[i-1][amount] : 0;
                    dp[i][amount] = withCoin + withoutCoin;
                }
            }
        }
        return dp[denominations.length-1][total];
    }

    public static void main(String[] args) {
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.countChangeBruteforce(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeTopDownDP(new int[]{1, 2, 3}, 5));
        System.out.println(coinChange.countChangeBottomUpDP(new int[]{1, 2, 3}, 5));
    }
}
