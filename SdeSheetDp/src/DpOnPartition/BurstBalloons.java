package SdeSheetDp.src.DpOnPartition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BurstBalloons {
    public int maxCoins(int[] nums) {
        // Convert the array to a List of Integers
        List<Integer> blist = Arrays.stream(nums).boxed().collect(Collectors.toList());

        // Add 1 at the beginning and end of the list to handle edge cases
        blist.add(1); // Add 1 at the end
        blist.add(0, 1); // Add 1 at the beginning

        int n = nums.length;

        // Initialize the DP table for tabulation approach
        // DP table of size (n+2)x(n+2) because we added 1 at both ends
        int[][] dp = new int[n+2][n+2];

        // Fill the DP table using a bottom-up approach
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= n; j++) {
                // Skip invalid ranges where i > j
                if (i > j) {
                    continue;
                }
                int maxCoins = Integer.MIN_VALUE;

                // Try bursting each balloon in the range [i, j] last
                for (int ind = i; ind <= j; ind++) {
                    // Calculate coins obtained by bursting balloon 'ind' last
                    int cost = blist.get(i-1) * blist.get(ind) * blist.get(j+1)
                            + dp[i][ind-1] + dp[ind+1][j];
                    maxCoins = Math.max(maxCoins, cost);
                }
                dp[i][j] = maxCoins; // Store the result in DP table
            }
        }
        return dp[1][n]; // Result is stored in dp[1][n]
    }

    // Recursive method with memoization
    private int getMaxCoins(int i, int j, List<Integer> blist, Integer[][] dp) {
        // Base case: no balloons to burst
        if (i > j) {
            return 0;
        }
        // Return cached result if already computed
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int maxCoins = Integer.MIN_VALUE;

        // Try bursting each balloon in the range [i, j] last
        for (int ind = i; ind <= j; ind++) {
            // Calculate coins obtained by bursting balloon 'ind' last
            int cost = blist.get(i-1) * blist.get(ind) * blist.get(j+1)
                    + getMaxCoins(i, ind-1, blist, dp)
                    + getMaxCoins(ind+1, j, blist, dp);
            maxCoins = Math.max(maxCoins, cost);
        }
        // Cache the result
        return dp[i][j] = maxCoins;
    }

    public static void main(String[] args) {
        BurstBalloons sol = new BurstBalloons();
        int[] nums = {3, 1, 5, 8}; // Example input

        // Calculate the maximum coins that can be obtained
        int result = sol.maxCoins(nums);
        System.out.println("Maximum coins obtainable: " + result);

        // Example for memoization approach
        List<Integer> blist = Arrays.stream(nums).boxed().collect(Collectors.toList());
        blist.add(1);
        blist.add(0, 1);
        int n = nums.length;
        Integer[][] dp = new Integer[n + 2][n + 2]; // Initialize DP table
        int resultMemo = sol.getMaxCoins(1, n, blist, dp);
        System.out.println("Maximum coins obtainable (Memoization): " + resultMemo);
    }
}
