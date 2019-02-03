package dp;

import java.util.Arrays;
import java.util.Set;

public class ZeroOneKnapSack {

    /**
     * Recursive Brute force
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int knapsackBruteForce(int[] profits, int[] weights, int capacity) {
        return knapsackBruteForceHelper(profits, weights, capacity, 0);
    }

    private int knapsackBruteForceHelper(int[] profits, int[] weights, int capacity, int index) {
        if (capacity <= 0 || index >= weights.length) {
            return 0;
        } else {
            int withItem = capacity >= weights[index] ?
                             profits[index] + knapsackBruteForceHelper(profits, weights, capacity - weights[index], index+1) :
                             0;
            int withoutItem = knapsackBruteForceHelper(profits, weights, capacity, index+1);
            return Math.max(withItem, withoutItem);
        }
    }

    /**
     * Topdown DP
     * @param profits
     * @param weights
     * @param capacity
     * @return
     */
    public int knapsackTopDown(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[capacity+1][weights.length];
        return knapsackTopDownHelper(profits, weights, capacity, 0, dp);
    }

    private int knapsackTopDownHelper(int[] profits, int[] weights, int capacity, int index, Integer[][] dp) {
        if (capacity <= 0 || index >= weights.length) {
            return 0;
        } else if (dp[capacity][index] == null) {
            int withItem = capacity >= weights[index] ?
                           profits[index] + knapsackTopDownHelper(profits, weights, capacity - weights[index], index+1, dp) :
                           0;
            int withoutItem = knapsackTopDownHelper(profits, weights, capacity, index+1, dp);
            dp[capacity][index] = Math.max(withItem, withoutItem);
        }
        return dp[capacity][index];
    }

    /**
     * Knapsack bottomup
     * @param profits
     * @param weights
     * @param maxCapacity
     * @return
     */
    public int knapsackBottomUp(int[] profits, int[] weights, int maxCapacity) {
        int numItems = weights.length;
        int[][] dp = new int[maxCapacity+1][numItems];

        for (int capacity = 1; capacity <= maxCapacity; capacity++) {
            for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
                int withItem = capacity >= weights[itemIndex] ?
                               profits[itemIndex] + (itemIndex > 0 ? dp[capacity - weights[itemIndex]][itemIndex-1] : 0) :
                               0;
                int withoutItem = itemIndex > 0 ? dp[capacity][itemIndex-1] : 0;
                dp[capacity][itemIndex] = Math.max(withItem, withoutItem);
            }
        }

        printSelectedItems(dp, weights, profits, maxCapacity);
        return dp[maxCapacity][numItems-1];
    }

    private void printSelectedItems(int[][] dp, int[] weights, int[] profits, int maxCapacity) {
        StringBuilder itemsBuilder = new StringBuilder();
        int numItems = weights.length;
        int totalProfit = dp[maxCapacity][numItems-1];
        int totalCapacity = maxCapacity;

        for (int itemIndex = numItems-1; itemIndex >= 0; itemIndex--) {
            if (itemIndex > 0 && totalProfit != dp[totalCapacity][itemIndex-1]) {
                itemsBuilder.append(weights[itemIndex]);
                itemsBuilder.append(',');
                totalCapacity -= weights[itemIndex];
                totalProfit -= profits[itemIndex];
            } else if (itemIndex == 0 && totalProfit != 0) {
                itemsBuilder.append(weights[itemIndex]);
            }
        }

        System.out.println(itemsBuilder.toString());
    }

    public int knapsackBottomUpOptimized(int[] profits, int[] weights, int maxCapacity) {
        int numItems = weights.length;
        int[] dp = new int[maxCapacity+1];

        for (int itemIndex = 0; itemIndex < numItems; itemIndex++) {
            for (int capacity = maxCapacity; capacity >= 1; capacity--) {
                int withItem = capacity >= weights[itemIndex] ?
                               profits[itemIndex] + dp[capacity - weights[itemIndex]] : 0;
                int withoutItem = dp[capacity];
                dp[capacity] = Math.max(withItem, withoutItem);
            }
        }

        return dp[maxCapacity];
    }

    public static void main(String[] args) {
        System.out.println("Testing bruteforce");
        System.out.println(new ZeroOneKnapSack().knapsackBruteForce(new int[] {4, 5, 3, 7}, new int[]{2, 3, 1, 4}, 5));
        System.out.println(new ZeroOneKnapSack().knapsackBruteForce(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(new ZeroOneKnapSack().knapsackBruteForce(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 6));

        System.out.println("Testing topdown dp");
        System.out.println(new ZeroOneKnapSack().knapsackTopDown(new int[] {4, 5, 3, 7}, new int[]{2, 3, 1, 4}, 5));
        System.out.println(new ZeroOneKnapSack().knapsackTopDown(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(new ZeroOneKnapSack().knapsackTopDown(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 6));

        System.out.println("Testing bottomup dp");
        System.out.println(new ZeroOneKnapSack().knapsackBottomUp(new int[] {4, 5, 3, 7}, new int[]{2, 3, 1, 4}, 5));
        System.out.println(new ZeroOneKnapSack().knapsackBottomUp(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(new ZeroOneKnapSack().knapsackBottomUp(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 6));

        System.out.println("Testing bottomup dp optimized");
        System.out.println(new ZeroOneKnapSack().knapsackBottomUpOptimized(new int[] {4, 5, 3, 7}, new int[]{2, 3, 1, 4}, 5));
        System.out.println(new ZeroOneKnapSack().knapsackBottomUpOptimized(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 7));
        System.out.println(new ZeroOneKnapSack().knapsackBottomUpOptimized(new int[] {1, 6, 10, 16}, new int[]{1, 2, 3, 5}, 6));
    }
}
