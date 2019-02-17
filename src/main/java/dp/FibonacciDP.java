package dp;

import java.util.Arrays;

public class FibonacciDP {

    /**
     * Memoized fibonacci calculator
     * @param n
     * @return
     */
    public int fibonacciNumberMemoized(int n) {
        int[] dp = new int[n+1];
        return fibonacciNumberHelper(n, dp);
    }

    private int fibonacciNumberHelper(int n, int[] dp) {
        if (n < 2) {
            return n;
        } else if (dp[n] == 0) {
            dp[n] = fibonacciNumberHelper(n-1, dp) + fibonacciNumberHelper(n-2, dp);
        }

        return dp[n];
    }

    /**
     * Bottom up DP
     * @param n
     * @return
     */
    public int fibonacciNumberBottomUp(int n) {
        int[] fibonacci = new int[n+1];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibonacci[i] = fibonacci[i-1] + fibonacci[i-2];
        }

        return fibonacci[n];
    }

    public int fibonacciNumberBottomUpOptimized(int n) {
        if (n < 2) {
            return n;
        }

        int first = 0;
        int second = 1;
        for (int i = 2; i <= n; i++) {
            int current = first + second;
            first = second;
            second = current;
        }
        return second;
    }

    public static void main(String[] args) {
        fibonacciDPDemo();
        fibonacciBottomUpDemo();
        fibonacciBottomUpOptimizedDemo();
    }

    private static void fibonacciDPDemo() {
        FibonacciDP fibonacciDP = new FibonacciDP();
        for (int i = 2; i <= 10; i++) {
            System.out.println(String.format("i = %d, fibonacci : %d", i, fibonacciDP.fibonacciNumberMemoized(i)));
        }
    }

    private static void fibonacciBottomUpDemo() {
        FibonacciDP fibonacciDP = new FibonacciDP();
        for (int i = 2; i <= 10; i++) {
            System.out.println(String.format("i = %d, fibonacci : %d", i, fibonacciDP.fibonacciNumberBottomUp(i)));
        }
    }

    private static void fibonacciBottomUpOptimizedDemo() {
        FibonacciDP fibonacciDP = new FibonacciDP();
        for (int i = 2; i <= 10; i++) {
            System.out.println(String.format("i = %d, fibonacci : %d", i, fibonacciDP.fibonacciNumberBottomUpOptimized(i)));
        }
    }
}
