package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMPMatcher {

    public static List<Integer> match(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] longestPrefixLengths = computePrefixFunction(pattern);
        int q = 0;  // number of characters matched.
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {   // scan the text from left to right
            while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
                q = longestPrefixLengths[q-1];   // next character does not match
            }

            if (pattern.charAt(q) == text.charAt(i)) {
                q = q + 1;  // next character matches
            }

            if (q == m) {   // is all of pattern matched?
                result.add(i - m + 1);  // pattern occurs with shift i - m + 1
                q = longestPrefixLengths[q-1];   // look for the next match
            }
        }

        return result;
    }

    private static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] longestPrefixLengths = new int[m];
        longestPrefixLengths[0] = 0;
        int k = 0;
        for (int q = 2; q <= m; q++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q-1)) {
                k = longestPrefixLengths[k-1];
            }

            if (pattern.charAt(k) == pattern.charAt(q-1)) {
                k = k + 1;
            }

            longestPrefixLengths[q-1] = k;
        }
        return longestPrefixLengths;
    }

    public static void main(String[] args) {
        testCase("abababacaba", "ababaca", Arrays.asList(2));
        testComputePrefix("ababbabbabbababbabb", new int[]{0, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 3, 4, 5, 6, 7, 8});
    }

    private static void testComputePrefix(String pattern, int[] expected) {
        System.out.println("Pattern : " + pattern);
        int[] actual = computePrefixFunction(pattern);
        System.out.println("prefixFunction : " + Arrays.toString(actual));
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);

        KMPMatcher matcher = new KMPMatcher();
        List<Integer> actual = matcher.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
