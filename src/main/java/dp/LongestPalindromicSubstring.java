package dp;

public class LongestPalindromicSubstring {

    /**
     * Bruteforce
     * @param str
     * @return
     */
    public int findLPSLengthBruteForce(String str) {
        return findLPSLengthBruteForceHelper(str, 0, str.length()-1);
    }

    private int findLPSLengthBruteForceHelper(String str, int start, int end) {
        if (start > end) {
            return 0;
        } else if (start == end) {
            return 1;
        }

        if (str.charAt(start) == str.charAt(end)) {
            int substrLen = end - start - 1;
            if (findLPSLengthBruteForceHelper(str, start+1, end-1) == substrLen) {
                return substrLen + 2;
            }
        }

        return Math.max(findLPSLengthBruteForceHelper(str, start+1, end),
                        findLPSLengthBruteForceHelper(str, start, end-1));
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
        }

        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        if (str.charAt(start) == str.charAt(end)) {
            int substrLen = end - start - 1;
            if (findLPSLengthTopDownDPHelper(str, start+1, end-1, dp) == substrLen) {
                dp[start][end] = substrLen + 2;
                return dp[start][end];
            }
        }

        dp[start][end] = Math.max(findLPSLengthTopDownDPHelper(str, start+1, end, dp),
                                  findLPSLengthTopDownDPHelper(str, start, end-1, dp));
        return dp[start][end];
    }

    /**
     * Bottom up DP
     * @param str
     * @return
     */
    public int findLPSLengthBottomUpDP(String str) {
        int[][] dp = new int[str.length()][str.length()];
        for (int i=0; i < str.length(); i++) {
            dp[i][i] = 1;
        }

        for (int start = str.length()-1; start >= 0; start--) {
            for (int end=start+1; end < str.length(); end++) {
                if (str.charAt(start) == str.charAt(end)) {
                    int substrLen = end - start - 1;
                    if (dp[start+1][end-1] == substrLen) {
                        dp[start][end] = substrLen+2;
                        continue;
                    }
                }

                dp[start][end] = Math.max(dp[start][end-1], dp[start+1][end]);
            }
        }

        return dp[0][str.length()-1];
    }

    public static void main(String[] args) {
        testCaseBruteforce("abdbca", 3);
        testCaseBruteforce("cddpd", 3);
        testCaseBruteforce("pqr", 1);

        testCaseTopDownDP("abdbca", 3);
        testCaseTopDownDP("cddpd", 3);
        testCaseTopDownDP("pqr", 1);
        testCaseTopDownDP("a", 1);

        testCaseBottomUpDP("abdbca", 3);
        testCaseBottomUpDP("cddpd", 3);
        testCaseBottomUpDP("pqr", 1);
        testCaseBottomUpDP("a", 1);
    }

    private static void testCaseBruteforce(String str, int expected) {
        int actual = new LongestPalindromicSubstring().findLPSLengthBruteForce(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseTopDownDP(String str, int expected) {
        int actual = new LongestPalindromicSubstring().findLPSLengthTopDownDP(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCaseBottomUpDP(String str, int expected) {
        int actual = new LongestPalindromicSubstring().findLPSLengthBottomUpDP(str);
        System.out.println(String.format("str=%s, lps length=%s", str, actual));
        if (expected != actual) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
