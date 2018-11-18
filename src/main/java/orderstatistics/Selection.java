package orderstatistics;

import util.Utils;

import java.util.Random;

import static util.Utils.getRandom;
import static util.Utils.swap;

public class Selection {

    public static final Random RANDOM = new Random();

    /**
     * Find i-th smallest element. i is 1-based.
     * @param array
     * @param i
     * @return
     */
    public static int randomizedSelect(int[] array, int i) {
        return randomizedSelect(array, i, 0, array.length-1);
    }

    private static int randomizedSelect(int[] array, int i, int left, int right) {
        if (left == right) {
            return array[left];
        }

        int partitionIndex = randomizedPartition(array, left, right);
        int k = partitionIndex - left + 1;
        if (k == i) {   // the pivot value is the answer
            return array[partitionIndex];
        } else if (i < k) {
            return randomizedSelect(array, i, left, partitionIndex-1);
        } else {
            return randomizedSelect(array, i - k, partitionIndex+1, right);
        }
    }

    private static int randomizedPartition(int[] array, int left, int right) {
        int randomIndex = getRandom(RANDOM, left, right);
        swap(array, randomIndex, right);

        int key = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= key) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i+1, right);
        return i+1;
    }

    /**
     * Find i-th smallest element. i is 1-based.
     * @param array
     * @param i
     * @return
     */
    public static int randomizedSelectIterative(int[] array, int i) {
        int left = 0, right = array.length-1;
        while (left < right) {
            int partitionIndex = randomizedPartition(array, left, right);
            int k = partitionIndex - left + 1;
            if (k == i) {
                return array[partitionIndex];
            } else if (i < k) {
                right = partitionIndex-1;
            } else {
                left = partitionIndex + 1;
                i = i - k;
            }
        }
        return array[left];
    }

    public static void main(String[] args) {
        testCase(new int[]{10, 1, 9, 2, 8, 3, 7, 4, 6, 5});
    }

    private static void testCase(int[] array) {
        System.out.println("Randomized select demo:");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%dth smallest element : %d\n", i+1, Selection.randomizedSelect(array, i+1));
        }

        System.out.println("Randomized iterative select demo:");
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%dth smallest element : %d\n", i+1, Selection.randomizedSelectIterative(array, i+1));
        }
    }
}
