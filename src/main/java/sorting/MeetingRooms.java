package sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/meeting-rooms/description/
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 */
public class MeetingRooms {

    public static class Interval {
        private int start;
        private int end;

        public Interval() {
            this(0, 0);
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, Comparator.comparing(interval -> interval.start));

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i-1].end) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
        System.out.println(meetingRooms.canAttendMeetings(
                new Interval[] {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)}));
        System.out.println(meetingRooms.canAttendMeetings(new Interval[] {new Interval(7, 10), new Interval(2, 4)}));
    }
}
