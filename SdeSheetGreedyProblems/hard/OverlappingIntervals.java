package SdeSheetGreedyProblems.hard;
import java.util.*;



public class OverlappingIntervals {

    static class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            // Compare based on the end time of intervals
            if (i1[1] < i2[1]) {
                return -1;
            } else if (i1[1] > i2[1]) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    public int eraseOverlapIntervals(int[][] intervals) {

        // Sort the intervals based on the end time using the custom comparator
        Arrays.sort(intervals, new IntervalComparator());

        int cnt = 0;  // Count of intervals to be removed
        int n = intervals.length;
        int endTime = intervals[0][1];  // End time of the first interval

        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= endTime) {
                // If the start time of the current interval is not less than the end time
                // of the previous interval, update the end time to the end of the current interval
                endTime = intervals[i][1];
            } else {
                // Overlapping interval found, increment count
                cnt++;
            }
        }

        return cnt;  // Return the number of intervals to be removed
    }

    public static void main(String[] args) {
        OverlappingIntervals solution = new OverlappingIntervals();
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(solution.eraseOverlapIntervals(intervals));  // Output: 1
    }
}
