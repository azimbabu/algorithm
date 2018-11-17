package sorting;

import java.util.Arrays;

import static util.Utils.swap;

public class DutchNationalFlag {

    public static void rearrange(int[] array) {
        int zeros = 0, twos = array.length-1;
        int i = 0;
        while (i <= twos) {
            if (array[i] == 0) {
                swap(array, zeros++, i++);
            } else if (array[i] == 2) {
                swap(array, twos--, i);
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        testCase(new int[]{0, 1, 2, 0, 1, 2});
        testCase(new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1});
    }

    private static void testCase(int[] array) {
        System.out.println("Before rearrange : " + Arrays.toString(array));
        DutchNationalFlag.rearrange(array);
        System.out.println("After rearrange : " + Arrays.toString(array));
    }
}
