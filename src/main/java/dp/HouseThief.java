package dp;

import java.util.Arrays;

public class HouseThief {

    /**
     * Bruteforce
     * @param wealth
     * @return
     */
    public int findMaxStealBruteforce(int[] wealth) {
        return findMaxStealBruteforceHelper(0, wealth);
    }

    private int findMaxStealBruteforceHelper(int current, int[] wealth) {
        if (current >= wealth.length) {
            return 0;
        } else {
            // steal from current house and skip one to steal next
            int includeCurrent = wealth[current] + findMaxStealBruteforceHelper(current + 2, wealth);
            // skip current house to steel from the adjacent house
            int skipCurrent = findMaxStealBruteforceHelper(current + 1, wealth);
            return Math.max(includeCurrent, skipCurrent);
        }
    }

    /**
     * Topdown DP
     * @param wealth
     * @return
     */
    public int findMaxStealTopDownDP(int[] wealth) {
        int[] dp = new int[wealth.length];
        Arrays.fill(dp, -1);
        return findMaxStealTopDownDPHelper(0, wealth, dp);
    }

    private int findMaxStealTopDownDPHelper(int current, int[] wealth, int[] dp) {
        if (current >= wealth.length) {
            return 0;
        } else if (dp[current] == -1) {
            // steal from current house and skip one to steal next
            int includeCurrent = wealth[current] + findMaxStealTopDownDPHelper(current + 2, wealth, dp);
            // skip current house to steel from the adjacent house
            int skipCurrent = findMaxStealTopDownDPHelper(current + 1, wealth, dp);
            dp[current] = Math.max(includeCurrent, skipCurrent);
        }
        return dp[current];
    }

    /**
     * Bottom up Dp
     * @param wealth
     * @return
     */
    public int findMaxStealBottomUpDP(int[] wealth) {
        if (wealth.length == 0) {
            return 0;
        } else if (wealth.length == 1) {
            return wealth[wealth.length-1];
        }

        int[] dp = new int[wealth.length];
        for (int current=wealth.length-1; current >= 0; current--) {
            // steal from current house and skip one to steal next
            int includeCurrent = wealth[current] + ((current+2) < wealth.length ? dp[current+2] : 0);
            // skip current house to steel from the adjacent house
            int skipCurrent = (current+1) < wealth.length ? dp[current+1] : 0;
            dp[current] = Math.max(includeCurrent, skipCurrent);
        }
        return dp[0];
    }

    /**
     * Bottom up DP Space optimized
     * @param wealth
     * @return
     */
    public int findMaxStealBottomUpDPOptimized(int[] wealth) {
        if (wealth.length == 0) {
            return 0;
        } else if (wealth.length == 1) {
            return wealth[wealth.length-1];
        }

        int nextMax = 0;
        int secondNextMax = 0;
        for (int current=wealth.length-1; current >= 0; current--) {
            // steal from current house and skip one to steal nextMax
            int includeCurrent = wealth[current] + ((current+2) < wealth.length ? secondNextMax : 0);
            // skip current house to steel from the adjacent house
            int skipCurrent = (current+1) < wealth.length ? nextMax : 0;
            int currentMax = Math.max(includeCurrent, skipCurrent);
            secondNextMax = nextMax;
            nextMax = currentMax;
        }
        return nextMax;
    }

    public static void main(String[] args) {
        testCaseBruteforce(new int[]{2, 5, 1, 3, 6, 2, 4}, 15);
        testCaseBruteforce(new int[]{2, 10, 14, 8, 1}, 18);

        testCaseTopDownDP(new int[]{2, 5, 1, 3, 6, 2, 4}, 15);
        testCaseTopDownDP(new int[]{2, 10, 14, 8, 1}, 18);

        testCaseBottomUpDP(new int[]{2, 5, 1, 3, 6, 2, 4}, 15);
        testCaseBottomUpDP(new int[]{2, 10, 14, 8, 1}, 18);

        testCaseBottomUpDPOptimized(new int[]{2, 5, 1, 3, 6, 2, 4}, 15);
        testCaseBottomUpDPOptimized(new int[]{2, 10, 14, 8, 1}, 18);
    }

    private static void testCaseBruteforce(int[] wealth, int expected) {
        int actual = new HouseThief().findMaxStealBruteforce(wealth);
        System.out.println(String.format("Wealth=%s, Max Steal=%s", Arrays.toString(wealth), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseTopDownDP(int[] wealth, int expected) {
        int actual = new HouseThief().findMaxStealTopDownDP(wealth);
        System.out.println(String.format("Wealth=%s, Max Steal=%s", Arrays.toString(wealth), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(int[] wealth, int expected) {
        int actual = new HouseThief().findMaxStealBottomUpDP(wealth);
        System.out.println(String.format("Wealth=%s, Max Steal=%s", Arrays.toString(wealth), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDPOptimized(int[] wealth, int expected) {
        int actual = new HouseThief().findMaxStealBottomUpDPOptimized(wealth);
        System.out.println(String.format("Wealth=%s, Max Steal=%s", Arrays.toString(wealth), actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
