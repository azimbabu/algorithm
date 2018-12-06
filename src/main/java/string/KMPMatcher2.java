package string;

import java.util.Arrays;
import java.util.List;

public class KMPMatcher2 {

    public static boolean match(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        int textIndex = 0;
        int patternIndex = 0;

        while (textIndex < text.length() && patternIndex < pattern.length()) {
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                textIndex++;
                patternIndex++;
            } else {
                if (patternIndex != 0) {
                    patternIndex = lps[patternIndex-1];
                } else {
                    textIndex++;
                }
            }
        }

        if (patternIndex == pattern.length()) {
            return true;
        } else {
            return false;
        }
    }

    private static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int index = 0;

        for (int i = 1; i < pattern.length();) {
            if (pattern.charAt(i) == pattern.charAt(index)) {
                lps[i] = index+1;
                i++;
                index++;
            } else {
                if (index != 0) {
                    index = lps[index-1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        testCase("abababacaba", "ababaca", true);
    }

    private static void testCase(String text, String pattern, boolean expected) {
        System.out.println("Text : " + text);
        System.out.println("Pattern : " + pattern);

        KMPMatcher2 matcher = new KMPMatcher2();
        boolean actual = matcher.match(text, pattern);
        System.out.println("Match : " + actual);
        if (actual != expected) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
