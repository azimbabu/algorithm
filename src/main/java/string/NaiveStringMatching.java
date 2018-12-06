package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaiveStringMatching {

    public static List<Integer> match(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> result = new ArrayList<>();

        for (int shift = 0; shift <= n - m; shift++) {
            if (compare(text, pattern, shift, shift + m - 1, 0, m-1)) {
                result.add(shift);
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
        testCase("000010001010001", "0001", Arrays.asList(1, 5, 11));
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        NaiveStringMatching stringMatching = new NaiveStringMatching();
        List<Integer> actual = stringMatching.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
        if (!actual.equals(expected)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
