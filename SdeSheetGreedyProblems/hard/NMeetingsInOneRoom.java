package SdeSheetGreedyProblems.hard;

import java.util.*;

public class NMeetingsInOneRoom {

    // Inner class representing a meeting with start time, end time, and position
    class Meeting {
        int start;
        int end;
        int pos;

        // Constructor to initialize the meeting object
        Meeting(int start, int end, int pos) {
            this.start = start;
            this.end = end;
            this.pos = pos;
        }
    }

    // Inner class to compare meetings by their end time using a Comparator
    class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            // Sort by end time. If equal, maintain original order
            if (m1.end < m2.end) {
                return -1;
            } else if (m1.end > m2.end) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Function to find the maximum number of meetings using a custom Comparator
    public int maxMeetingsWithComparator(int[] start, int[] end) {
        int n = start.length;
        Meeting[] meetings = new Meeting[n];

        // Step 1: Create Meeting objects
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(start[i], end[i], i + 1);
        }

        // Step 2: Sort meetings using a custom comparator by end time
        Arrays.sort(meetings, new MeetingComparator());

        // Step 3: Select maximum number of non-overlapping meetings
        int count = 1;
        int freeTime = meetings[0].end;

        List<Integer> meetingList = new ArrayList<>();
        meetingList.add(meetings[0].pos);  // Add first meeting

        for (int i = 1; i < n; i++) {
            if (meetings[i].start > freeTime) {  // Can attend this meeting
                count++;
                freeTime = meetings[i].end;  // Update end time
                meetingList.add(meetings[i].pos);  // Add meeting position
            }
        }

        // Optional: Print scheduled meetings
        System.out.println("Meetings scheduled (with Comparator): " + meetingList);

        return count;
    }

    // Function to find the maximum number of meetings using lambda for sorting
    public int maxMeetingsWithoutComparator(int[] start, int[] end) {
        int n = start.length;
        Meeting[] meetings = new Meeting[n];

        // Step 1: Create Meeting objects
        for (int i = 0; i < n; i++) {
            meetings[i] = new Meeting(start[i], end[i], i + 1);
        }

        // Step 2: Sort meetings by end time using lambda expression
        Arrays.sort(meetings, (a, b) -> {
            if (a.end < b.end) {
                return -1;
            } else if (a.end > b.end) {
                return 1;
            } else {
                return 0;
            }
        });

        // Step 3: Select maximum number of non-overlapping meetings
        int count = 1;
        int freeTime = meetings[0].end;

        List<Integer> meetingList = new ArrayList<>();
        meetingList.add(meetings[0].pos);  // Add first meeting

        for (int i = 1; i < n; i++) {
            if (meetings[i].start > freeTime) {  // Can attend this meeting
                count++;
                freeTime = meetings[i].end;  // Update end time
                meetingList.add(meetings[i].pos);  // Add meeting position
            }
        }

        // Optional: Print scheduled meetings
        System.out.println("Meetings scheduled (without Comparator): " + meetingList);

        return count;
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        NMeetingsInOneRoom sol = new NMeetingsInOneRoom();

        // Sample input arrays
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};

        // Test method with Comparator
        int maxMeetingsWithComp = sol.maxMeetingsWithComparator(start, end);
        System.out.println("Maximum number of meetings (with Comparator): " + maxMeetingsWithComp);

        // Test method without Comparator (using lambda)
        int maxMeetingsWithoutComp = sol.maxMeetingsWithoutComparator(start, end);
        System.out.println("Maximum number of meetings (without Comparator): " + maxMeetingsWithoutComp);
    }
}
