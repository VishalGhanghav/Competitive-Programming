package SdeSheetGreedyProblems.hard;

import java.util.*;

public class JobSequencingProblem {

    // Class representing a job with id, deadline, and profit
    class Job {
        int id, deadline, profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    // Comparator to sort jobs by profit in descending order
    class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job j1, Job j2) {
            // j1 is first or existing and j2 is next
            // -1 means already proper. 1 means switch
            if (j1.profit > j2.profit) {
                return -1;
            } else if (j1.profit < j2.profit) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Function to find the maximum profit and the number of jobs done.
    // Way 1: Using simple greedy approach with an array for deadlines
    public int[] JobScheduling(Job arr[], int n) {
        int maxProfit = 0, maxDeadline = 0, cnt = 0;
        // Sort jobs in descending order by profit
        Arrays.sort(arr, new JobComparator());

        // Calculate maxDeadline
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(arr[i].deadline, maxDeadline);
        }

        // Array to keep track of which slots are filled
        int[] hash = new int[maxDeadline + 1];
        Arrays.fill(hash, -1);

        // Assign jobs to slots from their deadlines to 1
        for (int i = 0; i < n; i++) {
            for (int j = arr[i].deadline; j >= 1; j--) {
                if (hash[j] == -1) {
                    cnt += 1;
                    maxProfit += arr[i].profit;
                    hash[j] = arr[i].id;
                    break;
                }
            }
        }
        return new int[]{cnt, maxProfit};
    }

    // Way 2: Using DSU (Disjoint Set Union) to reduce the second loop
    int[] parent;  // DSU parent array

    // Find function for DSU
    int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);  // Path compression
    }

    // Union function for DSU
    void union(int x, int y) {
        parent[find(x)] = find(y);  // Union by deadline
    }

    // Function to find the maximum profit and number of jobs using DSU
    public int[] JobSchedulingDSU(Job arr[], int n) {
        int maxProfit = 0, cnt = 0;
        Arrays.sort(arr, new JobComparator());

        // Find maximum deadline to create DSU parent array
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(arr[i].deadline, maxDeadline);
        }

        // Initialize DSU parent array, with each parent[i] = i
        parent = new int[maxDeadline + 1];
        for (int i = 0; i <= maxDeadline; i++) {
            parent[i] = i;
        }

        // Process each job in order of decreasing profit
        for (int i = 0; i < n; i++) {
            int availableSlot = find(arr[i].deadline);  // Find the nearest available slot
            if (availableSlot > 0) {
                cnt += 1;
                maxProfit += arr[i].profit;
                union(availableSlot, availableSlot - 1);  // Union to mark the slot as taken
            }
        }
        return new int[]{cnt, maxProfit};
    }

    // Main method to test both approaches
    public static void main(String[] args) {
        JobSequencingProblem problem = new JobSequencingProblem();

        // Job array: {Job id, Job deadline, Job profit}
        Job[] arr = {
                problem.new Job(1, 2, 100),
                problem.new Job(2, 1, 19),
                problem.new Job(3, 2, 27),
                problem.new Job(4, 1, 25),
                problem.new Job(5, 3, 15)
        };

        int n = arr.length;

        // Test Way 1
        int[] result1 = problem.JobScheduling(arr, n);
        System.out.println("Way 1 (Greedy): Jobs done = " + result1[0] + ", Max Profit = " + result1[1]);

        // Test Way 2
        int[] result2 = problem.JobSchedulingDSU(arr, n);
        System.out.println("Way 2 (DSU): Jobs done = " + result2[0] + ", Max Profit = " + result2[1]);
    }
}

