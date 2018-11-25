package sorting;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * https://leetcode.com/problems/merge-intervals/description/
 */
public class MergeIntervals {

    public static class Interval {
        int start;
        int end;

        public Interval() {
            this(0, 0);
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Interval{" +
                   "start=" + start +
                   ", end=" + end +
                   '}';
        }
    }

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.isEmpty()) {
            return Collections.emptyList();
        }

        Collections.sort(intervals, Comparator.comparing(interval -> interval.start));

        LinkedList<Interval> result = new LinkedList<>();
        for (Interval interval : intervals) {
            if (result.isEmpty() || result.getLast().end < interval.start) {
                result.add(interval);
            } else {
                result.getLast().end = Math.max(result.getLast().end, interval.end);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        System.out.println(mergeIntervals.merge(Arrays.asList(
                new Interval(1, 3),
                new Interval(2, 6),
                new Interval(8, 10),
                new Interval(15, 18))));

        System.out.println(mergeIntervals.merge(Arrays.asList(
                new Interval(1, 4),
                new Interval(4, 5))));

        System.out.println(mergeIntervals.merge(Arrays.asList(
                new Interval(1, 4),
                new Interval(2, 3))));
    }
}
