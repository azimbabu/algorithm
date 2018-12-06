package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabinKarpMatcher2 {

    private static final int PRIME = 101;

    public static List<Integer> match(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        long patternHash = calculateHash(pattern, m);
        long textHash = calculateHash(text, m);
        List<Integer> result = new ArrayList<>();

        for (int shift = 0; shift <= n - m; shift++) {
            if (patternHash == textHash && compare(text, pattern, shift, shift + m - 1, 0, m-1)) {
                result.add(shift);
            }

            if (shift < n - m) {
                textHash = calculateRollingHash(text, textHash, shift, shift + m, m);
            }
        }

        return result;
    }

    private static boolean compare(String text, String pattern,
                                   int textStart, int textEnd,
                                   int patternStart, int patternEnd) {
        for (int i = textStart, j = patternStart; i <= textEnd && j <= patternEnd; i++, j++) {
            if (text.charAt(i) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    private static long calculateHash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long calculateRollingHash(String text,
                                             long currentHash,
                                             int outgoingIndex,
                                             int incomingIndex,
                                             int patternLength) {
        long newHash = currentHash - text.charAt(outgoingIndex);
        newHash /= PRIME;
        newHash += text.charAt(incomingIndex) * Math.pow(PRIME, patternLength-1);
        return newHash;
    }

    public static void main(String[] args) {
        testCase("acaabc", "aab", Arrays.asList(2));
        testCase("2359023141526739921", "31415", Arrays.asList(6));
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        RabinKarpMatcher2 stringMatching = new RabinKarpMatcher2();
        List<Integer> actual = stringMatching.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
