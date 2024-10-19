package SdeSheetGreedyProblems.hard;

import java.util.*;

public class MinimumPlatformsForTrain {
    static class Train {
        int start;
        int end;

        Train(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // Way 1: Using sorting and two-pointer approach
    static int findPlatformWay1(int arr[], int dep[]) {
        // Sort both arrival and departure times
        Arrays.sort(arr);
        Arrays.sort(dep);

        int n = arr.length;
        int i = 0, j = 0, cnt = 0, maxCnt = 0;

        // Compare arrival and departure times
        while (i < n) {
            if (arr[i] <= dep[j]) {
                // A train arrives before the next departs, increment count
                cnt++;
                i++;
            } else {
                // A train departs before the next arrives, decrement count
                cnt--;
                j++;
            }
            // Track the maximum number of platforms needed at any time
            maxCnt = Math.max(cnt, maxCnt);
        }
        return maxCnt;  // Return the maximum number of platforms required
    }

    // Way 2: Using a priority queue (min-heap) to manage platform allocations
    static int findPlatformWay2(int arr[], int dep[], int n) {
        // Create a list of trains with start (arrival) and end (departure) times
        List<Train> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Train(arr[i], dep[i]));
        }

        // Sort trains by arrival time
        Collections.sort(list, (a, b) -> a.start - b.start);

        // Min-heap to track the earliest train to depart
        PriorityQueue<Train> minHeap = new PriorityQueue<>((a, b) -> a.end - b.end);

        // Add the first train to the heap
        minHeap.add(list.get(0));

        // Iterate through the remaining trains
        for (int i = 1; i < n; i++) {
            int currArrival = list.get(i).start;
            int currDeparture = list.get(i).end;

            // If the current train's arrival is after the earliest train's departure, reuse the platform
            if (minHeap.peek().end < currArrival) {
                minHeap.poll(); // Remove the train that has already departed
            }
            // Add the current train to the heap (new platform or reuse existing)
            minHeap.add(new Train(currArrival, currDeparture));
        }

        // The size of the heap gives the number of platforms needed
        return minHeap.size();
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800}; // Arrival times of trains
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000}; // Departure times of trains
        int n = arr.length;

        // Testing Way 1
        System.out.println("Way 1 (Two-pointer): Minimum platforms needed = " + findPlatformWay1(arr, dep));

        // Testing Way 2
        System.out.println("Way 2 (Priority Queue): Minimum platforms needed = " + findPlatformWay2(arr, dep, n));
    }
}
