package util;

import java.util.Random;

public final class Utils {

    private Utils() {
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int getRandom(Random random, int min, int max) {
        return min + random.nextInt(max - min + 1);
    }
}
