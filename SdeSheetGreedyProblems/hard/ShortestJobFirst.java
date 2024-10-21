package SdeSheetGreedyProblems.hard;

import java.util.Arrays;

public class ShortestJobFirst {

    // Function to calculate the average waiting time
    static int solve(int bt[]) {
        int n = bt.length;

        // Sort burst times in ascending order to minimize waiting time
        Arrays.sort(bt);

        // Variables to store the total waiting time and the running time
        int t = 0, waitingTime = 0;

        // Iterate over the sorted burst times
        for (int i = 0; i < n; i++) {
            // Add the current running time to the total waiting time
            waitingTime += t;
            // Update the running time with the current burst time
            t += bt[i];
        }

        // Calculate and return the average waiting time
        return waitingTime / n;
    }

    public static void main(String[] args) {
        // Example burst times for processes
        int[] burstTimes = {5, 1, 8, 4};

        // Call the solve function to calculate the average waiting time
        int averageWaitingTime = solve(burstTimes);

        // Print the result
        System.out.println("Average Waiting Time: " + averageWaitingTime);
    }
}
