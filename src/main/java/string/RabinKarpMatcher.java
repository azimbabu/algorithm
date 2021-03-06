package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabinKarpMatcher {

    private static final int BASE = 256;
    private static final int MOD = 13;

    public static List<Integer> match(String text, String pattern) {
        return match(text, pattern, BASE, MOD);
    }

    private static List<Integer> match(String text, String pattern, int d, int q) {
        int n = text.length();
        int m = pattern.length();
        int h = 1;

        int p = 0;
        int t = 0;

        for (int i = 0; i < m; i++) {   // pre-processing
            p = Math.floorMod((d * p + pattern.charAt(i)), q);
            t = Math.floorMod((d * t + text.charAt(i)), q);

            if (i > 0) {    // calculate d ^ (m-1) mod q and avoid overflow
                h = Math.floorMod(Math.floorMod(h, q) * Math.floorMod(d, q), q);    // (x * y) mod p = ( (x mod p)*(y mod p) ) mod p
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int shift = 0; shift <= n-m; shift++) {
            if (p == t && compare(text, pattern, shift, shift + m - 1, 0, m-1)) {
                result.add(shift);
            }

            if (shift < n - m) {
                t = Math.floorMod((d * (t - h * text.charAt(shift)) + text.charAt(shift + m)), q);
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

    public static void main(String[] args) {
        testCase("acaabc", "aab", Arrays.asList(2));
        testCase("2359023141526739921", "31415", Arrays.asList(6));
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        RabinKarpMatcher stringMatching = new RabinKarpMatcher();
        List<Integer> actual = stringMatching.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
