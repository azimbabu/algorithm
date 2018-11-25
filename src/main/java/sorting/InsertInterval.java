package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * https://leetcode.com/problems/insert-interval/description/
 */
public class InsertInterval {

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

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i++));
        }

        while (i < intervals.size() && overlaps(intervals.get(i), newInterval)) {
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            i++;
        }

        result.add(newInterval);

        while (i < intervals.size() && intervals.get(i).start > newInterval.end) {
            result.add(intervals.get(i++));
        }

        return result;
    }

    private boolean overlaps(Interval intervalA, Interval intervalB) {
        return !(intervalA.end < intervalB.start || intervalB.end < intervalA.start);
    }

    public static void main(String[] args) {
        InsertInterval insertInterval = new InsertInterval();
        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(1, 3),
                new Interval(6, 9)
        ), new Interval(2, 5)));

        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(1, 2),
                new Interval(3, 5),
                new Interval(6, 7),
                new Interval(8, 10),
                new Interval(12, 16)
        ), new Interval(4, 8)));

        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(1, 5)
        ), new Interval(2, 7)));

        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(1, 5)
        ), new Interval(0, 0)));

        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(1, 5)
        ), new Interval(6, 7)));

        System.out.println(insertInterval.insert(Arrays.asList(
                new Interval(3, 5),
                new Interval(12, 15)
        ), new Interval(6, 6)));
    }

}
