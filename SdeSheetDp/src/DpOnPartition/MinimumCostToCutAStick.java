package SdeSheetDp.src.DpOnPartition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinimumCostToCutAStick {
    public int minCost(int n, int[] cuts) {
    // Convert the array of cut positions into a List of Integers
    List<Integer> cutsList = Arrays.stream(cuts)  // Create an IntStream from the array
            .boxed()      // Convert IntStream to Stream<Integer>
            .collect(Collectors.toList()); // Collect to List<Integer>

    int c = cutsList.size(); // Number of cuts
    // Integer[][] dp = new Integer[c+1][c+1];
    // cutsList.add(0); // Add 0 to the list of cuts (starting point)
    // cutsList.add(n); // Add n to the list of cuts (ending point)

    // Initialize DP table for tabulation approach
    // dp[i][j] represents the minimum cost to cut the rod between cuts i and j
    int[][] dp = new int[c + 2][c + 2];
    cutsList.add(0); // Add starting point of the rod
    cutsList.add(n); // Add ending point of the rod
    Collections.sort(cutsList); // Sort the list of cuts

    // Fill DP table using bottom-up approach
    for (int i = c; i >= 1; i--) {
        for (int j = 1; j <= c; j++) {
            if (i > j) {
                continue; // Skip if no valid range
            }
            int minCost = Integer.MAX_VALUE;

            // Calculate the minimum cost for making cuts between i and j
            for (int ind = i; ind <= j; ind++) {
                // Calculate cost of making a cut at position ind
                // + dp[i][ind-1]: cost of making cuts in the left segment
                // + dp[ind+1][j]: cost of making cuts in the right segment
                int cost = cutsList.get(j + 1) - cutsList.get(i - 1)
                        + dp[i][ind - 1]
                        + dp[ind + 1][j];
                minCost = Math.min(cost, minCost);
            }
            dp[i][j] = minCost; // Store the minimum cost in the DP table
        }
    }

    // The result is stored in dp[1][c], which represents the minimum cost for the entire rod
    return dp[1][c];
}

    // Recursive function for memoization approach
    public int getMinCost(int i, int j, List<Integer> cuts, Integer[][] dp) {
        // Base case: No cuts needed if i > j
        if (i > j) {
            return 0;
        }
        // Return the cached result if already computed
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int minCost = Integer.MAX_VALUE;

        // Calculate the minimum cost for making cuts between i and j
        for (int ind = i; ind <= j; ind++) {
            // Calculate cost of making a cut at position ind
            // + getMinCost(i, ind-1): cost of making cuts in the left segment
            // + getMinCost(ind+1, j): cost of making cuts in the right segment
            int cost = cuts.get(j + 1) - cuts.get(i - 1)
                    + getMinCost(i, ind - 1, cuts, dp)
                    + getMinCost(ind + 1, j, cuts, dp);
            minCost = Math.min(cost, minCost);
        }

        // Cache the computed result
        return dp[i][j] = minCost;
    }

    public static void main(String[] args) {
        MinimumCostToCutAStick sol = new MinimumCostToCutAStick();
        int n = 7; // Length of the rod
        int[] cuts = {1, 3, 4, 5}; // Positions where cuts are to be made

        // Calculate minimum cost to cut the rod
        int minCost = sol.minCost(n, cuts);
        System.out.println("Minimum cost to cut the rod: " + minCost);

        // Example for memoization approach
        List<Integer> cutsList = Arrays.stream(cuts)  // Create an IntStream from the array
                .boxed()      // Convert IntStream to Stream<Integer>
                .collect(Collectors.toList()); // Collect to List<Integer>
        cutsList.add(0); // Add starting point
        cutsList.add(n); // Add ending point
        Collections.sort(cutsList); // Sort the list

        Integer[][] dp = new Integer[cutsList.size()][cutsList.size()]; // Initialize DP table
        int minCostMemo = sol.getMinCost(1, cutsList.size() - 2, cutsList, dp);
        System.out.println("Minimum cost to cut the rod (Memoization): " + minCostMemo);
    }
}
