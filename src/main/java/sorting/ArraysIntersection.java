package sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 * Arrays intersection.
 * Drawback : arrays are mutated.
 * Time complexity : O(n1 lg n1 + n2 lg n2) where n1 = length of 1st array and n2 = length of 2nd array
 */
public class ArraysIntersection {

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        Set<Integer> commons = new HashSet<>();
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                commons.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[commons.size()];
        int i = 0;
        for (int value : commons) {
            result[i++] = value;
        }
        return result;
    }

    public static void main(String[] args) {
        testCase(new int[]{1, 2, 2, 1}, new int[]{2, 2});
        testCase(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
    }

    private static void testCase(int[] nums1, int[] nums2) {
        System.out.println("Array1 : " + Arrays.toString(nums1));
        System.out.println("Array2 : " + Arrays.toString(nums2));
        System.out.println("Intersection : " + Arrays.toString(new ArraysIntersection().intersection(nums1, nums2)));
    }
}
