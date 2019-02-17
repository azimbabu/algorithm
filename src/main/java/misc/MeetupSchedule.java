package misc;

import java.util.*;

public class MeetupSchedule {

    private static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int investment(List<Integer> startTimes, List<Integer> endTimes) {
        if (startTimes.isEmpty()) {
            return 0;
        }

        int minDay = Integer.MAX_VALUE;
        List<Interval> intervals = new ArrayList<>();
        for (int i=0; i < startTimes.size(); i++) {
            intervals.add(new Interval(startTimes.get(i), endTimes.get(i)));
            minDay = Math.min(minDay, startTimes.get(i));
        }

        Collections.sort(intervals, (interval1, interval2) -> {
            int compare = Integer.compare(interval1.end, interval2.end);
            if (compare != 0) {
                return compare;
            } else {
                return Integer.compare(interval1.start, interval2.start);
            }
        });

        int day = minDay;
        int numMeetings = 0;
        for (Interval interval : intervals) {
            if (day < interval.start) {
                day = interval.start+1;
                numMeetings++;
            } else if (interval.start <= day && day <= interval.end) {
                day++;
                numMeetings++;
            }
        }
        return numMeetings;
    }

    public static void main(String[] args) {
        System.out.println(investment(Arrays.asList(1, 2, 3, 3, 3), Arrays.asList(2, 2, 3, 4, 4)));
    }
}
