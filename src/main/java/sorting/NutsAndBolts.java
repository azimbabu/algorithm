package sorting;

import java.util.Arrays;

import static util.Utils.swap;

public class NutsAndBolts {

    public static void matchPairs(char[] nuts, char[] bolts) {
        matchPairs(nuts, bolts, 0, bolts.length-1);
    }

    private static void matchPairs(char[] nuts, char[] bolts, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(bolts, left, right, nuts[right]);
            partition(nuts, left, right, bolts[partitionIndex]);

            matchPairs(nuts, bolts, left, partitionIndex-1);
            matchPairs(nuts, bolts, partitionIndex+1, right);
        }
    }

    private static int partition(char[] array, int left, int right, int pivot) {
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            } else if (array[j] == pivot) {
                swap(array, j, right);
                j--;
            }
        }
        swap(array, i+1, right);
        return i+1;
    }

    public static void main(String[] args) {
        // Nuts and bolts are represented as array of characters
        char nuts[] = {'@', '#', '$', '%', '^', '&'};
        char bolts[] = {'$', '%', '&', '^', '@', '#'};

        // Method based on quick sort which matches nuts and bolts
        NutsAndBolts.matchPairs(nuts, bolts);

        System.out.println("Matched nuts and bolts are : ");
        System.out.println("Nuts: " + Arrays.toString(nuts));
        System.out.println("Bolts: " + Arrays.toString(bolts));
    }
}
