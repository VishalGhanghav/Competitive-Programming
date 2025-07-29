package SdeSheetHeaps.HeapsHardProblems;

import java.util.PriorityQueue;

public class ConnectRopesWithMinimumCost {
    // TC: O(N log N), SC: O(N)
    // Reason: Each insertion/removal in minHeap takes O(log N), and we do it N-1 times
    public static int minCost(int[] arr) {
        // code here
        //Approach
        //We need smallest ropes at top and get their addition and sort back
        //Use minHeap

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int rope : arr) {
            minHeap.add(rope);
        }

        int cost = 0;

        while (!(minHeap.size() == 1)) {
            int smallest = minHeap.poll();
            int secondSmallest = minHeap.poll();

            int sum = smallest + secondSmallest;
            minHeap.add(sum);
            cost += sum;
        }

        return cost;
    }

    public static void main(String[] args) {
        int[] ropes1 = {4, 3, 2, 6};
        int[] ropes2 = {1, 2, 3, 4, 5};

        System.out.println("Min cost to connect ropes1: " + minCost(ropes1)); // Output: 29
        System.out.println("Min cost to connect ropes2: " + minCost(ropes2)); // Output: 33
    }
}
