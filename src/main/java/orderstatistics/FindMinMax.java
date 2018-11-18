package orderstatistics;

import java.util.Optional;

public class FindMinMax {

    public static class MinMax {
        private final int min;
        private final int max;

        public MinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    /**
     * Find min and max with at most 3 n/2 comparisons.
     * @param array
     * @return
     */
    public Optional<MinMax> minMax(int[] array) {
        int n = array.length;
        if (n == 0) {
            // mo min max for an emtpy array
            return Optional.empty();
        }

        int min, max, start;
        if (n % 2 == 0) {
            // if n is even, find initial values of min and max
            if (array[0] <= array[1]) {
                min = array[0];
                max = array[1];
            } else {
                min = array[1];
                max = array[0];
            }

            start = 2;
        } else {
            // if n is odd, set first value as min and max.
            min = max = array[0];
            start = 1;
        }

        for (int i = start; i < n; i += 2) {
            int smaller, larger;
            if (array[i] <= array[i+1]) {
                smaller = array[i];
                larger = array[i+1];
            } else {
                smaller = array[i+1];
                larger = array[i];
            }

            min = Math.min(min, smaller);
            max = Math.max(max, larger);
        }

        return Optional.of(new MinMax(min, max));
    }
}
