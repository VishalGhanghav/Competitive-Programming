package SdeSheetGraphs.ShortestPath;

import java.util.*;

// Class Name: MinimumMultiplicationsToReachEnd
class MinimumMultiplicationsToReachEnd {

    // Helper class kept as inner class per instructions
    class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    /**
     * Time Complexity: O(100000 * N) - Where 100000 is the modulo range and N is the array length.
     * Space Complexity: O(100000) - To store the distance array and the BFS queue.
     */
    int minimumMultiplications(int[] arr, int start, int end) {

        // Your code here
        // Create a queue for storing the numbers as a result of multiplication
        // of the numbers in the array and the start number.
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(start, 0));

        // Create a dist array to store the no. of multiplications to reach
        // a particular number from the start number.
        int[] dist = new int[100000];
        for (int i = 0; i < 100000; i++) dist[i] = (int) (1e9);
        dist[start] = 0;
        int mod = 100000;
        int n = arr.length;
        if (start == end) {
            return 0;
        }
        // O(100000 * N)

        // Multiply the start no. with each of numbers in the arr
        // until we get the end no.
        while (!q.isEmpty()) {
            int node = q.peek().first;
            int steps = q.peek().second;
            q.remove();

            for (int i = 0; i < n; i++) {
                int num = (arr[i] * node) % mod;

                // If the no. of multiplications are less than before
                // in order to reach a number, we update the dist array.
                if (steps + 1 < dist[num]) {
                    dist[num] = steps + 1;

                    // Whenever we reach the end number
                    // return the calculated steps
                    if (num == end) return steps + 1;
                    q.add(new Pair(num, steps + 1));
                }
            }
        }
        // If the end no. is unattainable.
        return -1;
    }

    public static void main(String[] args) {
        MinimumMultiplicationsToReachEnd solver = new MinimumMultiplicationsToReachEnd();

        int[] arr = {2, 5, 7};
        int start = 3;
        int end = 30;

        int result = solver.minimumMultiplications(arr, start, end);

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Start: " + start + ", End: " + end);
        System.out.println("Minimum Multiplications: " + result);
    }
}
