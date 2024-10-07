package SdeSheetArrays.hard;

import java.util.*;

public class MergeIntervals {

    // Striver's Approach using List<List<Integer>>
    public static int[][] mergeOverlappingIntervalsList(int[][] intervals) {
        int m = intervals.length;
        int n = 2;

        // Sort intervals based on the starting point
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0]; // Sort by start of the interval
            }
        });

        List<List<Integer>> mergedIntervals = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            // if intervals are empty or (1,6) (7,8) merge not possible as 6<7.
            // Create new interval
            if (mergedIntervals.isEmpty() || mergedIntervals.get(mergedIntervals.size() - 1).get(1) < intervals[i][0]) {
                mergedIntervals.add(Arrays.asList(intervals[i][0], intervals[i][1]));
            } else {
                // if (1,6) (5,8) merge can be done.
                // Also we can have (1,8) (3,4).
                // So take max of both at the 2nd position
                int endOfMergedInterval = Math.max(intervals[i][1], mergedIntervals.get(mergedIntervals.size() - 1).get(1));
                // At first position set
                mergedIntervals.get(mergedIntervals.size() - 1).set(1, endOfMergedInterval);
            }
        }

        // Convert List<List<Integer>> to int[][] array
        int[][] res = new int[mergedIntervals.size()][2];
        for (int i = 0; i < mergedIntervals.size(); i++) {
            res[i][0] = mergedIntervals.get(i).get(0);
            res[i][1] = mergedIntervals.get(i).get(1);
        }

        return res;
    }

    // Approach using Stack
    public static int[][] mergeOverlappingIntervalsStack(int[][] intervals) {
        // Sort intervals on the basis of start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Stack will store <start, end>
        Stack<int[]> st = new Stack<>();
        // 1st interval pushed
        st.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // now second element
            int startPoint2 = intervals[i][0];
            int endPoint2 = intervals[i][1];

            int[] poppedArray = st.pop();
            int startPoint1 = poppedArray[0];
            int endPoint1 = poppedArray[1];

            // merge condition 1 3   2 6
            if (endPoint1 >= startPoint2) { // 3>=2
                // 1 4    2 3
                int endMax = Math.max(endPoint1, endPoint2);

                int[] merge = new int[]{startPoint1, endMax};
                st.push(merge);
            } else {
                // no merge
                // Push both arrays
                st.push(poppedArray);
                st.push(intervals[i]);
            }
        }

        // Create the output array to store merged intervals
        int output[][] = new int[st.size()][2];
        for (int i = output.length - 1; i >= 0; i--) {
            int[] popArray = st.pop();
            output[i][0] = popArray[0];
            output[i][1] = popArray[1];
        }

        return output;
    }

    public static void main(String[] args) {
        // Input intervals
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};

        // Using Striver's Approach (List-based)
        System.out.println("Merged Intervals (List-based approach):");
        int[][] mergedIntervalsList = mergeOverlappingIntervalsList(intervals);
        for (int[] interval : mergedIntervalsList) {
            System.out.println(Arrays.toString(interval));
        }

        // Using Stack-based approach
        System.out.println("\nMerged Intervals (Stack-based approach):");
        int[][] mergedIntervalsStack = mergeOverlappingIntervalsStack(intervals);
        for (int[] interval : mergedIntervalsStack) {
            System.out.println(Arrays.toString(interval));
        }
    }
}

