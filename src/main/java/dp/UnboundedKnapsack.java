package dp;

import java.util.Arrays;

public class UnboundedKnapsack {

    /**
     * Bruteforce
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int solveKnapsackBruteForce(int[] profits, int[] weights, int capacity) {
        return knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int index) {
        if (capacity == 0 || index == profits.length) {
            return 0;
        }

        int withItem = capacity >= weights[index] ?
                       profits[index] + knapsackRecursive(profits, weights, capacity - weights[index], index) :
                       0;
        int withoutItem = knapsackRecursive(profits, weights, capacity, index+1);

        return Math.max(withItem, withoutItem);
    }

    /**
     * Topdown DP
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int solveKnapsackTopDownDP(int[] profits, int[] weights, int capacity) {
        int[][] dp = new int[profits.length][1+capacity];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return solveKnapsackTopDownDPHelper(profits, weights, capacity, 0, dp);
    }

    private int solveKnapsackTopDownDPHelper(int[] profits, int[] weights, int capacity, int index, int[][] dp) {
        if (capacity == 0 || index == profits.length) {
            return 0;
        } else if (dp[index][capacity] == -1) {
            int withItem = capacity >= weights[index] ?
                           profits[index] + solveKnapsackTopDownDPHelper(profits, weights, capacity - weights[index], index, dp) :
                           0;
            int withoutItem = solveKnapsackTopDownDPHelper(profits, weights, capacity, index+1, dp);
            dp[index][capacity] = Math.max(withItem, withoutItem);
        }
        return dp[index][capacity];
    }

    public int solveKnapsackBottomUpDP(int[] profits, int[] weights, int maxCapacity) {
        int[][] dp = new int[profits.length][1+maxCapacity];
        for (int i = 0; i < profits.length; i++) {
            for (int capacity = 0; capacity <= maxCapacity; capacity++) {
                if (i == 0) {
                    dp[i][capacity] = (capacity >=  weights[i] ? profits[i] : 0);
                } else {
                    int withItem = capacity >= weights[i] ? profits[i] + dp[i-1][capacity-weights[i]] : 0;
                    int withoutItem = dp[i-1][capacity];
                    dp[i][capacity] = Math.max(withItem, withoutItem);
                }
            }
        }

        printSelectedItems(dp, weights);

        return dp[profits.length-1][maxCapacity];
    }

    private void printSelectedItems(int[][] dp, int[] weights) {
        StringBuilder result = new StringBuilder();
        int item = dp.length - 1;
        int capacity = dp[0].length-1;
        while (capacity != 0) {
            if (item == 0 || dp[item][capacity] != dp[item-1][capacity]) {
                result.append(item);
                result.append(',');
                capacity = capacity - weights[item];
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
        UnboundedKnapsack knapsack = new UnboundedKnapsack();
        System.out.println(knapsack.solveKnapsackBruteForce(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 8));
        System.out.println(knapsack.solveKnapsackBruteForce(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 6));

        System.out.println(knapsack.solveKnapsackTopDownDP(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 8));
        System.out.println(knapsack.solveKnapsackTopDownDP(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 6));

        System.out.println(knapsack.solveKnapsackBottomUpDP(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 8));// {3,1}
        System.out.println(knapsack.solveKnapsackBottomUpDP(new int[] {15, 50, 60, 90}, new int[] {1, 3, 4, 5}, 6));// {3, 0}
    }
}
