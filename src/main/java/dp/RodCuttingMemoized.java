package dp;

import java.util.Arrays;

public class RodCuttingMemoized {

    public int memoizedCutRod(int[] profits, int n) {
        int[] revenues = new int[n + 1];
        Arrays.fill(revenues, Integer.MIN_VALUE);
        return memoizedCutRodAux(profits, n, revenues);
    }

    private int memoizedCutRodAux(int[] profits, int n, int[] revenues) {
        if (revenues[n] >= 0) {
            return revenues[n];
        }

        int revenue;
        if (n == 0) {
            revenue = 0;
        } else {
            revenue = Integer.MIN_VALUE;
            for (int i=1; i <= n; i++) {
                revenue = Math.max(revenue, profits[i-1] + memoizedCutRodAux(profits, n - i, revenues));
            }
        }

        revenues[n] = revenue;
        return revenue;
    }

    public static void main(String[] args) {
        int[] profits = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        RodCuttingMemoized rodCuttingMemoized = new RodCuttingMemoized();
        for (int i = 0; i < profits.length; i++) {
            System.out.println(String.format("i = %d, Revenue = %d", i, rodCuttingMemoized.memoizedCutRod(profits, i+1)));
        }
    }
}
