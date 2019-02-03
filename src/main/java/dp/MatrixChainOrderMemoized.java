package dp;

import java.util.Arrays;

public class MatrixChainOrderMemoized {

    public static class MatrixChainOrderResult {
        private final int[][] costs;
        private final int[][] splits;

        public MatrixChainOrderResult(int[][] costs, int[][] splits) {
            this.costs = costs;
            this.splits = splits;
        }
    }

    public MatrixChainOrderResult matrixChainOrder(int[] matrixDimensions) {
        int n = matrixDimensions.length - 1;
        int[][] costs = new int[n][n];
        int[][] splits = new int[n][n];

        for (int[] row : costs) {
            Arrays.fill(row, -1);
        }

        matrixChainOrderHelper(matrixDimensions, costs, splits, 0, n-1);
        return new MatrixChainOrderResult(costs, splits);
    }

    private int matrixChainOrderHelper(int[] matrixDimensions, int[][] costs, int[][] splits, int i, int j) {
        if (i == j) {
            costs[i][i] = 0;
        } else if (i > j) {
            return costs[i][j];
        } else if (costs[i][j] == -1) {
            int minCost = Integer.MAX_VALUE;
            int minSplit = -1;
            for (int k = i; k < j; k++) {
                int cost = matrixChainOrderHelper(matrixDimensions, costs, splits, i, k) +
                           matrixChainOrderHelper(matrixDimensions, costs, splits, k+1, j) +
                           matrixDimensions[i] * matrixDimensions[k+1] * matrixDimensions[j+1];
                if (cost < minCost) {
                    minCost = cost;
                    minSplit = k;
                }
            }
            costs[i][j] = minCost;
            splits[i][j] = minSplit;
        }
        return costs[i][j];
    }

    public void buildOptimalParenthesis(int[][] splits, int i, int j, StringBuilder parenthesisBuilder) {
        if (i == j) {
            parenthesisBuilder.append('A');
            parenthesisBuilder.append(i);
        } else {
            parenthesisBuilder.append('(');
            buildOptimalParenthesis(splits, i, splits[i][j], parenthesisBuilder);
            buildOptimalParenthesis(splits, splits[i][j] + 1, j, parenthesisBuilder);
            parenthesisBuilder.append(')');
        }
    }

    public static void main(String[] args) {
        MatrixChainOrderMemoized matrixChainOrderMemoized = new MatrixChainOrderMemoized();

        int[] matrixDimensions = {30, 35, 15, 5, 10, 20, 25};
        MatrixChainOrderResult result = matrixChainOrderMemoized.matrixChainOrder(matrixDimensions);
        System.out.println("Costs : " + Arrays.deepToString(result.costs));
        System.out.println("Splits: " + Arrays.deepToString(result.splits));

        int numMatrices = matrixDimensions.length - 1;
        System.out.println("Optimal Cost: " + result.costs[0][numMatrices-1]);

        StringBuilder parenthesisBuilder = new StringBuilder();
        matrixChainOrderMemoized.buildOptimalParenthesis(result.splits, 0, numMatrices-1, parenthesisBuilder);
        System.out.println("Optimal Solution: " + parenthesisBuilder.toString());
    }
}
