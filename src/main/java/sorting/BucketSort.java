package sorting;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    public static void sort(double[] array) {
        int n = array.length;
        List<Double>[] buckets = new LinkedList[n];

        for (int i = 0; i < n; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            List<Double> bucket = buckets[(int) (n * array[i])];
            ((LinkedList<Double>)bucket).addLast(array[i]);
        }

        for (int i = 0, j = 0; i < buckets.length; i++) {
            List<Double> bucket = buckets[i];
            insertionSort(bucket);
            while (!bucket.isEmpty()) {
                array[j++] = ((LinkedList<Double>)bucket).removeFirst();
            }
        }
    }

    private static void insertionSort(List<Double> nums) {
        int n = nums.size();
        for (int i=1; i < n; i++) {
            double key = nums.get(i);
            int j = i-1;
            while (j >= 0 && Double.compare(nums.get(j), key) > 0) {
                nums.set(j+1, nums.get(j));
                j--;
            }
            nums.set(j+1, key);
        }
    }

    public static void main(String[] args) {
        testCase(new double[] {0.78, 0.17, 0.39, 0.26, 0.72, 0.94, 0.21, 0.12, 0.23, 0.68});
    }

    private static void testCase(double[] array) {
        System.out.println("Unsorted : " + Arrays.toString(array));
        BucketSort.sort(array);
        System.out.println("Sorted : " + Arrays.toString(array));
    }
}
