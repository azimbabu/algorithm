package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiniteAutomationMatcher {

    public static List<Integer> match(String text, String pattern) {
        int[][] transitionFunction = computeTransitionFunction(pattern);

        int n = text.length();
        int m = pattern.length();
        List<Integer> result = new ArrayList<>();

        for (int i = 0, state = 0; i < n; i++) {
            state = transitionFunction[state][text.charAt(i) - 'a'];
            if (state == m) {
                result.add(i - m + 1);  // pattern occurs with shift i - m + 1
            }
        }

        return result;
    }

    private static int[][] computeTransitionFunction(String pattern) {
        int m = pattern.length();
        int[][] transitionFunction = new int[m+1][26];

        for (int state = 0; state <= m; state++) {
            for (char c = 'a'; c <= 'z'; c++) {
                int k = Math.min(m+1, state + 2);
                do {
                    k--;
                } while (k > 0 && !isSuffix(pattern, k, state, c));

                transitionFunction[state][c-'a'] = k;
            }
        }

        return transitionFunction;
    }

    /**
     * find if pattern.substring(0, k)
     * @param pattern
     * @param k
     * @param q
     * @param c
     * @return
     */
    private static boolean isSuffix(String pattern, int k, int q, char c) {
        // Pk suffix of Pqa
        if (pattern.charAt(k-1) != c) {
            return false;
        }

        for (int i = k-2, j = q-1; i >= 0; i--, j--) {
            if (pattern.charAt(i) != pattern.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        testCase("abababacaba", "ababaca", Arrays.asList(2));
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);

        FiniteAutomationMatcher matcher = new FiniteAutomationMatcher();
        List<Integer> actual = matcher.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
