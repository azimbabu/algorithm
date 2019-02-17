package dp;

import java.util.Arrays;

public class MaximumRibbonCut {

    /**
     * Brute force
     * @param ribbonLengths
     * @param totalLength
     * @return
     */
    public int countRibbonPiecesBruteForce(int[] ribbonLengths, int totalLength) {
        int result = countRibbonPiecesBruteForceHelper(ribbonLengths, 0, totalLength);
        return result != Integer.MIN_VALUE ? result : -1;
    }

    private int countRibbonPiecesBruteForceHelper(int[] ribbonLengths, int index, int totalLength) {
        if (totalLength == 0) {
            return 0;
        } else if (index == ribbonLengths.length) {
            return Integer.MIN_VALUE;
        } else {
            int withCurrent = totalLength >= ribbonLengths[index] ?
                              countRibbonPiecesBruteForceHelper(ribbonLengths, index, totalLength - ribbonLengths[index]) :
                              Integer.MIN_VALUE;
            withCurrent = withCurrent != Integer.MIN_VALUE ? withCurrent+1 : withCurrent;
            int withoutCurrent = countRibbonPiecesBruteForceHelper(ribbonLengths, index+1, totalLength);
            return Math.max(withCurrent, withoutCurrent);
        }
    }

    /**
     * Top down DP
     * @param ribbonLengths
     * @param totalLength
     * @return
     */
    public int countRibbonPiecesTopDownDP(int[] ribbonLengths, int totalLength) {
        int[][] dp = new int[ribbonLengths.length][1+totalLength];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = countRibbonPiecesTopDownDPHelper(ribbonLengths, 0, totalLength, dp);
        return result != Integer.MIN_VALUE ? result : -1;
    }

    private int countRibbonPiecesTopDownDPHelper(int[] ribbonLengths, int index, int totalLength, int[][] dp) {
        if (totalLength == 0) {
            return 0;
        } else if (index == ribbonLengths.length) {
            return Integer.MIN_VALUE;
        } else if (dp[index][totalLength] == -1) {
            int withCurrent = totalLength >= ribbonLengths[index] ?
                              countRibbonPiecesTopDownDPHelper(ribbonLengths, index, totalLength - ribbonLengths[index], dp) :
                              Integer.MIN_VALUE;
            withCurrent = withCurrent != Integer.MIN_VALUE ? withCurrent+1 : withCurrent;
            int withoutCurrent = countRibbonPiecesTopDownDPHelper(ribbonLengths, index+1, totalLength, dp);
            dp[index][totalLength] = Math.max(withCurrent, withoutCurrent);
        }
        return dp[index][totalLength];
    }

    public int countRibbonPiecesBottomUpDP(int[] ribbonLengths, int totalLength) {
        int[][] dp = new int[ribbonLengths.length][1+totalLength];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i < dp.length; i++) {
            for (int length = 1; length <= totalLength; length++) {
                int withCurrent = length >= ribbonLengths[i] ? dp[i][length - ribbonLengths[i]] : Integer.MIN_VALUE;
                withCurrent = withCurrent != Integer.MIN_VALUE ? withCurrent + 1 : withCurrent;
                int withoutCurrent = i > 0 ? dp[i-1][length] : Integer.MIN_VALUE;
                dp[i][length] = Math.max(withCurrent, withoutCurrent);
            }
        }

        return dp[ribbonLengths.length-1][totalLength] != Integer.MIN_VALUE ? dp[ribbonLengths.length-1][totalLength] : -1;
    }

    public static void main(String[] args) {
        MaximumRibbonCut ribbonCut = new MaximumRibbonCut();
        System.out.println(ribbonCut.countRibbonPiecesBruteForce(new int[]{2, 3, 5}, 5));   // 2
        System.out.println(ribbonCut.countRibbonPiecesBruteForce(new int[]{2, 3}, 7));      // 3
        System.out.println(ribbonCut.countRibbonPiecesBruteForce(new int[]{3, 5, 7}, 13));  // 3
        System.out.println(ribbonCut.countRibbonPiecesBruteForce(new int[]{3, 5}, 7));      // -1

        System.out.println(ribbonCut.countRibbonPiecesTopDownDP(new int[]{2, 3, 5}, 5));   // 2
        System.out.println(ribbonCut.countRibbonPiecesTopDownDP(new int[]{2, 3}, 7));      // 3
        System.out.println(ribbonCut.countRibbonPiecesTopDownDP(new int[]{3, 5, 7}, 13));  // 3
        System.out.println(ribbonCut.countRibbonPiecesTopDownDP(new int[]{3, 5}, 7));      // -1

        System.out.println(ribbonCut.countRibbonPiecesBottomUpDP(new int[]{2, 3, 5}, 5));   // 2
        System.out.println(ribbonCut.countRibbonPiecesBottomUpDP(new int[]{2, 3}, 7));      // 3
        System.out.println(ribbonCut.countRibbonPiecesBottomUpDP(new int[]{3, 5, 7}, 13));  // 3
        System.out.println(ribbonCut.countRibbonPiecesBottomUpDP(new int[]{3, 5}, 7));      // -1
    }
}
