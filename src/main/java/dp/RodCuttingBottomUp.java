package dp;

import java.util.Arrays;

public class RodCuttingBottomUp {

    public int bottomUpCutRod(int[] profits, int n) {
        int[] revenues = new int[n+1];
        revenues[0] = 0;

        for (int i = 1; i <= n; i++) {
            int revenue = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                revenue = Math.max(revenue, profits[j-1] + revenues[i - j]);
            }
            revenues[i] = revenue;
        }
        return revenues[n];
    }

    public int[][] extendedBottomUpCutRod(int[] profits, int n) {
        int[] revenues = new int[n + 1];
        int[] sizes = new int[n+1];
        revenues[0] = 0;

        for (int i = 1; i <= n; i++) {
            int revenue = Integer.MIN_VALUE;
            int size = i;
            for (int j = 1; j <= i; j++) {
                if (revenue < profits[j-1] + revenues[i - j]) {
                    revenue = profits[j-1] + revenues[i - j];
                    size = j;
                }
            }
            revenues[i] = revenue;
            sizes[i] = size;
        }

        return new int[][] {revenues, sizes};
    }

    private void printCutRodSolution(int[] profits, int n) {
        int[][] result = this.extendedBottomUpCutRod(profits, n);

        int[] revenues = result[0];
        int[] sizes = result[1];

        StringBuilder solutionBuilder = new StringBuilder();
        int i = n;
        while (i > 0) {
            solutionBuilder.append(sizes[i]);
            solutionBuilder.append(',');
            i -= sizes[i];
        }

        if (solutionBuilder.length() > 0) {
            solutionBuilder.deleteCharAt(solutionBuilder.length() - 1);
        }

        System.out.println(String.format("n = %d, Revenue = %d, Size = %s", n, revenues[n], solutionBuilder.toString()));
    }

    public static void main(String[] args) {
        testBottomUp();
        testExtendedBottomUp();
    }

    private static void testBottomUp() {
        int[] profits = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        RodCuttingBottomUp rodCuttingBottomUp = new RodCuttingBottomUp();
        for (int i = 0; i < profits.length; i++) {
            System.out.println(String.format("i = %d, Revenue = %d", i, rodCuttingBottomUp.bottomUpCutRod(profits, i+1)));
        }
    }

    private static void testExtendedBottomUp() {
        int[] profits = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        RodCuttingBottomUp rodCuttingBottomUp = new RodCuttingBottomUp();
        for (int i = 0; i < profits.length; i++) {
            rodCuttingBottomUp.printCutRodSolution(profits, i+1);
        }
    }
}
