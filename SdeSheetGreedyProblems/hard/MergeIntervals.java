package SdeSheetGreedyProblems.hard;

import java.util.*;

public class MergeIntervals {

    // Comparator for sorting intervals based on start time, and if equal, by end time
    static class IntervalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1[0] < i2[0]) {
                return -1;
            } else if (i1[0] > i2[0]) {
                return 1;
            } else {
                return i1[1] - i2[1];
            }
        }
    }

    // Approach 1: Using Comparator and Arrays
    public static int[][] mergeUsingComparator(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals based on the starting point
        Arrays.sort(intervals, new IntervalComparator());

        // Temporary array to store merged intervals
        int[][] temp = new int[intervals.length][2];
        int index = 0; // Track position in temp array

        // Start with the first interval
        temp[index] = intervals[0];

        // Iterate over the intervals to merge overlapping ones
        for (int i = 1; i < intervals.length; i++) {
            if (temp[index][1] >= intervals[i][0]) { // Overlap condition
                // Merge by updating the end time
                temp[index][1] = Math.max(temp[index][1], intervals[i][1]);
            } else {
                // No overlap, move to the next interval
                index++;
                temp[index] = intervals[i];
            }
        }

        // Build the result array with only merged intervals
        int[][] result = new int[index + 1][2];
        for (int i = 0; i <= index; i++) {
            result[i] = temp[i];
        }

        return result;
    }

    // Approach 2: Using List<List<Integer>>
    public static int[][] mergeUsingList(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals based on the starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // List to store merged intervals
        List<List<Integer>> mergedIntervals = new ArrayList<>();

        // Iterate through each interval
        for (int[] interval : intervals) {
            // If list is empty or no overlap, add new interval
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1).get(1) < interval[0]) {
                mergedIntervals.add(Arrays.asList(interval[0], interval[1]));
            } else {
                // Merge intervals by updating the end time
                int newEnd = Math.max(mergedIntervals.get(mergedIntervals.size() - 1).get(1), interval[1]);
                mergedIntervals.get(mergedIntervals.size() - 1).set(1, newEnd);
            }
        }

        // Convert List<List<Integer>> back to int[][]
        int[][] result = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            result[i][0] = mergedIntervals.get(i).get(0);
            result[i][1] = mergedIntervals.get(i).get(1);
        }

        return result;
    }

    // Approach 3: Using Stack
    public static int[][] mergeUsingStack(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Sort the intervals based on the starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Stack to store merged intervals
        Stack<int[]> stack = new Stack<>();
        stack.push(intervals[0]); // Push the first interval

        // Iterate through each interval and merge
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] top = stack.peek(); // Get the last interval on the stack

            // Check if intervals overlap
            if (top[1] >= current[0]) {
                top[1] = Math.max(top[1], current[1]); // Merge intervals
            } else {
                stack.push(current); // No overlap, push the interval
            }
        }

        // Build the result array from the stack
        int[][] result = new int[stack.size()][2];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    // Main function to test the methods
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3}, {2, 6}, {8, 10}, {15, 18}
        };

        // Using Comparator and Arrays
        int[][] result1 = mergeUsingComparator(intervals);
        System.out.println("Using Comparator: " + Arrays.deepToString(result1));

        // Using List<List<Integer>>
        int[][] result2 = mergeUsingList(intervals);
        System.out.println("Using List: " + Arrays.deepToString(result2));

        // Using Stack
        int[][] result3 = mergeUsingStack(intervals);
        System.out.println("Using Stack: " + Arrays.deepToString(result3));
    }
}
