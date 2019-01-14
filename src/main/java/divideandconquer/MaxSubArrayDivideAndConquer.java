package divideandconquer;

import java.util.Arrays;
import java.util.Objects;

public class MaxSubArrayDivideAndConquer {

    public static class MaxSubArrayResult {
        private final int start;
        private final int end;
        private final int sum;

        public MaxSubArrayResult(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }
            MaxSubArrayResult that = (MaxSubArrayResult) o;
            return start == that.start &&
                   end == that.end &&
                   sum == that.sum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end, sum);
        }

        @Override
        public String toString() {
            return "MaxSubArrayResult{" +
                   "start=" + start +
                   ", end=" + end +
                   ", sum=" + sum +
                   '}';
        }
    }

    public MaxSubArrayResult findMaximumSubarray(int[] array) {
        return findMaximumSubarray(array, 0, array.length-1);
    }

    private MaxSubArrayResult findMaximumSubarray(int[] array, int low, int high) {
        if (low == high) {  // base case: only one element
            return new MaxSubArrayResult(low, high, array[low]);
        } else {
            int mid = low + (high - low)/2;
            MaxSubArrayResult leftResult = findMaximumSubarray(array, low, mid);
            MaxSubArrayResult rightResult = findMaximumSubarray(array, mid + 1, high);
            MaxSubArrayResult crossingResult = findMaxCrossingSubarray(array, low, mid, high);

            if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossingResult.sum) {
                return leftResult;
            } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossingResult.sum) {
                return rightResult;
            } else {
                return crossingResult;
            }
        }
    }

    private MaxSubArrayResult findMaxCrossingSubarray(int[] array, int low, int mid, int high) {
        int leftMaxSum = Integer.MIN_VALUE;
        int leftMaxIndex = -1;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > leftMaxSum) {
                leftMaxSum = sum;
                leftMaxIndex = i;
            }
        }

        int rightMaxSum = Integer.MIN_VALUE;
        int rightMaxIndex = -1;
        sum = 0;
        for (int i = mid+1; i <= high; i++) {
            sum += array[i];
            if (sum > rightMaxSum) {
                rightMaxSum = sum;
                rightMaxIndex = i;
            }
        }

        return new MaxSubArrayResult(leftMaxIndex, rightMaxIndex, leftMaxSum + rightMaxSum);
    }

    public static void main(String[] args) {
        testCase(new int[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7}, new MaxSubArrayResult(7, 10, 43));
    }

    private static void testCase(int[] array, MaxSubArrayResult expected) {
        MaxSubArrayResult actual = new MaxSubArrayDivideAndConquer().findMaximumSubarray(array);
        System.out.println(String.format("Array=%s, SubArray=%s", Arrays.toString(array), actual));
        if (!expected.equals(actual)) {
            throw new AssertionError(String.format("Expected=%s, Actual=%s", expected, actual));
        }
    }
}
