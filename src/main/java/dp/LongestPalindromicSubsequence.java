package dp;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

    /**
     * Bruteforce
     * @param str
     * @return
     */
    public int findLPSLengthBruteforce(String str) {
        return findLPSLengthBruteforceHelper(str, 0, str.length() - 1);
    }

    private int findLPSLengthBruteforceHelper(String str, int start, int end) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return 1;
        } else if (str.charAt(start) == str.charAt(end)) {
            return 2 + findLPSLengthBruteforceHelper(str, start + 1, end - 1);
        } else {
            return Math.max(findLPSLengthBruteforceHelper(str, start + 1, end),
                            findLPSLengthBruteforceHelper(str, start, end - 1));
        }
    }

    /**
     * Topdown DP
     * @param str
     * @return
     */
    public int findLPSLengthTopDownDP(String str) {
        int[][] dp = new int[str.length()][str.length()];
        return findLPSLengthTopDownDPHelper(str, 0, str.length()-1, dp);
    }

    private int findLPSLengthTopDownDPHelper(String str, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return 1;
        } else if (dp[start][end] == 0) {
            if (str.charAt(start) == str.charAt(end)) {
                dp[start][end] = 2 + findLPSLengthTopDownDPHelper(str, start+1, end-1, dp);
            } else {
                dp[start][end] = Math.max(findLPSLengthTopDownDPHelper(str, start+1, end, dp),
                                          findLPSLengthTopDownDPHelper(str, start, end-1, dp));
            }
        }
        return dp[start][end];
    }

    public int findLPSLengthBottomUpDP(String str) {
        // dp[i][j] stores the length of LPS from index 'i' to index 'j'
        int[][] dp = new int[str.length()][str.length()];
        // every sequence with one element is a palindrome of length 1
        for (int i=0; i < str.length(); i++) {
            dp[i][i] = 1;
        }

        for (int start=str.length()-1; start >= 0; start--) {
            for (int end=start+1; end < str.length(); end++) {
                if (str.charAt(start) == str.charAt(end)) {
                    dp[start][end] = 2 + dp[start+1][end-1];
                } else {
                    dp[start][end] = Math.max(dp[start+1][end], dp[start][end-1]);
                }
            }
        }

        return dp[0][str.length()-1];
    }

    public static void main(String[] args) {
        testCaseBruteforce("abdbca", 5);
        testCaseBruteforce("cddpd", 3);
        testCaseBruteforce("pqr", 1);

        testCaseTopDownDP("abdbca", 5);
        testCaseTopDownDP("cddpd", 3);
        testCaseTopDownDP("pqr", 1);

        testCaseBottomUpDP("abdbca", 5);
        testCaseBottomUpDP("cddpd", 3);
        testCaseBottomUpDP("pqr", 1);
    }

    private static void testCaseBruteforce(String str, int expected) {
        int actual = new LongestPalindromicSubsequence().findLPSLengthBruteforce(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseTopDownDP(String str, int expected) {
        int actual = new LongestPalindromicSubsequence().findLPSLengthTopDownDP(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(String str, int expected) {
        int actual = new LongestPalindromicSubsequence().findLPSLengthBottomUpDP(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
