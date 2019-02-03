package dp;

import java.util.Arrays;

public class RodCutting {

    /**
     * Bruteforce
     * @param lengths
     * @param prices
     * @param rodLength
     * @return
     */
    public int solveRodCuttingBruteforce(int[] lengths, int[] prices, int rodLength) {
        return solveRodCuttingBruteforceHelper(lengths, prices, 0, rodLength);
    }

    private int solveRodCuttingBruteforceHelper(int[] lengths, int[] prices, int index, int rodLength) {
        if (rodLength == 0 || index == lengths.length) {
            return 0;
        } else {
            int withCurrent = rodLength >= lengths[index] ?
                              prices[index] + solveRodCuttingBruteforceHelper(lengths, prices, index, rodLength - lengths[index]) :
                              0;
            int withoutCurrent = solveRodCuttingBruteforceHelper(lengths, prices, index + 1, rodLength);
            return Math.max(withCurrent, withoutCurrent);
        }
    }

    /**
     * Topdown DP
     * @param lengths
     * @param prices
     * @param rodLength
     * @return
     */
    public int solveRodCuttingTopDownDP(int[] lengths, int[] prices, int rodLength) {
        int[][] dp = new int[lengths.length][rodLength+1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveRodCuttingTopDownDPHelper(lengths, prices, 0, rodLength, dp);
    }

    private int solveRodCuttingTopDownDPHelper(int[] lengths, int[] prices, int index, int rodLength, int[][] dp) {
        if (rodLength == 0 || index == lengths.length) {
            return 0;
        } else if (dp[index][rodLength] == -1) {
            int withCurrent = rodLength >= lengths[index] ?
                              prices[index] + solveRodCuttingTopDownDPHelper(lengths, prices, index, rodLength - lengths[index], dp) :
                              0;
            int withoutCurrent = solveRodCuttingTopDownDPHelper(lengths, prices, index + 1, rodLength, dp);
            dp[index][rodLength] = Math.max(withCurrent, withoutCurrent);
        }
        return dp[index][rodLength];
    }

    public int solveRodCuttingBottomUpDP(int[] lengths, int[] prices, int rodLength) {
        int[][] dp = new int[lengths.length][rodLength+1];
        for (int i = 0; i < lengths.length; i++) {
            for (int length = 1; length <= rodLength; length++) {
                int withCurrent = length >= lengths[i] ?
                                  prices[i] + dp[i][length - lengths[i]]:
                                  0;
                int withoutCurrent = i > 0 ? dp[i-1][length] : 0;
                dp[i][length] = Math.max(withCurrent, withoutCurrent);
            }
        }

        printSelections(dp, lengths);

        return dp[lengths.length-1][rodLength];
    }

    private void printSelections(int[][] dp, int[] lengths) {
        StringBuilder result = new StringBuilder();
        int item = lengths.length-1;
        int rodLength = dp[0].length-1;
        while (rodLength != 0) {
            if (item == 0 || dp[item][rodLength] != dp[item-1][rodLength]) {
                result.append(lengths[item]);
                result.append(',');
                rodLength = rodLength - lengths[item];
            } else {
                item--;
            }
        }

        if (result.length() != 0) {
            result.deleteCharAt(result.length()-1);
        }
        System.out.println("Selected : " + result.toString());
    }

    public static void main(String[] args) {
        RodCutting rodCutting = new RodCutting();
        System.out.println(rodCutting.solveRodCuttingBruteforce(new int[]{1, 2, 3, 4, 5}, new int[]{2, 6, 7, 10, 13}, 5));
        System.out.println(rodCutting.solveRodCuttingTopDownDP(new int[]{1, 2, 3, 4, 5}, new int[]{2, 6, 7, 10, 13}, 5));
        System.out.println(rodCutting.solveRodCuttingBottomUpDP(new int[]{1, 2, 3, 4, 5}, new int[]{2, 6, 7, 10, 13}, 5));
    }
}
