package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabinKarpMatcher {

    public static List<Integer> match(String text, String pattern) {
        return match(text, pattern, 256, 31);
    }

    private static List<Integer> match(String text, String pattern, int d, int q) {
        int n = text.length();
        int m = pattern.length();
        int h = Math.floorMod((int) Math.pow(d, m-1), q);

        int p = 0;
        int t = 0;
        List<Integer> validShifts = new ArrayList<>();

        for (int i = 0; i < m; i++) {   // preprocessing
            p = Math.floorMod((d * p + pattern.charAt(i)), q);
            t = Math.floorMod((d * t + text.charAt(i)), q);
        }

        for (int shift = 0; shift <= n - m; shift++) {  // matching
            if (p == t && compare(text, pattern, shift)) {
                validShifts.add(shift);
            }

            if (shift < n - m) {
                t = Math.floorMod((d * (t - text.charAt(shift) * h) + text.charAt(shift + m)), q);
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
        testCase("2359023141526739921", "31415", Arrays.asList(6));
    }

    private static void testCase(String text, String pattern, List<Integer> expected) {
        RabinKarpMatcher stringMatching = new RabinKarpMatcher();
        List<Integer> actual = stringMatching.match(text, pattern);
        System.out.println("Valid shifts : " + actual);
//        if (!actual.equals(expected)) {
//            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
//        }
    }
}
