package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaiveStringMatching {

    public static List<Integer> match(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        List<Integer> validShifts = new ArrayList<>();

        for (int shift = 0; shift <= n - m; shift++) {
            if (compare(text, pattern, shift)) {
                validShifts.add(shift);
            }
        }

        return validShifts;
    }

    private static boolean compare(String text, String pattern, int shift) {
        int m = pattern.length();
        for (int i = 0; i < m; i++) {
            if (text.charAt(shift + i) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        testCase("acaabc", "aab", Arrays.asList(2));
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
