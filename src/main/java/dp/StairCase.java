package dp;

import java.util.Arrays;

public class StairCase {

    public int countWaysBruteForce(int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        }
        return countWaysBruteForce(n - 1) + countWaysBruteForce(n - 2) + countWaysBruteForce(n - 3);
    }

    public int countWaysTopDownDP(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        return countWaysTopDownDPHelper(n ,dp);
    }

    private int countWaysTopDownDPHelper(int n, int[] dp) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        } else if (dp[n] == -1) {
            dp[n] = countWaysTopDownDPHelper(n-1, dp) +
                    countWaysTopDownDPHelper(n-2, dp) +
                    countWaysTopDownDPHelper(n-3, dp);
        }
        return dp[n];
    }

    public int countWaysBottomUpDP(int n) {
        int[] dp = new int[n+1];
        for (int i=0; i <= n; i++) {
            if (i == 0) {
                dp[i] = 1;
            } else {
                dp[i] = (i-1 >= 0 ? dp[i-1] : 0) + (i-2 >= 0 ? dp[i-2] : 0) + (i-3 >= 0 ? dp[i-3] : 0);
            }
        }
        return dp[n];
    }

    public int countWaysBottomUpDP2(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i=3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n];
    }

    public int countWaysBottomUpDP3(int n) {
        if (n < 2) {
            return 1;
        }

        int number0 = 1;  // n = 0
        int number1 = 1; // n = 1
        int number2 = 2;  // n = 2

        for (int i=3; i <= n; i++) {
            int current = number0 + number1 + number2;
            number0 = number1;
            number1 = number2;
            number2 = current;
        }

        return number2;
    }

    public static void main(String[] args) {
        StairCase stairCase = new StairCase();

        for (int n = 1; n <= 10; n++) {
            System.out.println(String.format("n=%s, numWays=%s", n, stairCase.countWaysBruteForce(n)));
            System.out.println(String.format("n=%s, numWays=%s", n, stairCase.countWaysTopDownDP(n)));
            System.out.println(String.format("n=%s, numWays=%s", n, stairCase.countWaysBottomUpDP(n)));
            System.out.println(String.format("n=%s, numWays=%s", n, stairCase.countWaysBottomUpDP2(n)));
            System.out.println(String.format("n=%s, numWays=%s", n, stairCase.countWaysBottomUpDP3(n)));
        }

    }
}
