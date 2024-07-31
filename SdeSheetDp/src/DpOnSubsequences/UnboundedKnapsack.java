package SdeSheetDp.src.DpOnSubsequences;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        int N = 4; // Number of items
        int W = 8; // Maximum weight capacity of knapsack
        int[] val = {6,1,7,7}; // Values of items
        int[] wt = {1,3,4,5}; // Weights of items

        System.out.println(knapSack(N, W, val, wt)); // Expected output: 48
        //The optimal choice is to pick the 1st element 8 times.
    }

    public static int knapSack(int N, int W, int[] val, int[] wt) {
        Integer[][] dp = new Integer[N + 1][W + 1];
        // index (N) and sum (W) is changing
        return fillKnapsack(N, W, wt, val, dp);
    }

    private static int fillKnapsack(int n, int capacity, int[] wt, int[] val, Integer[][] dp) {
        // Base case: If no items are left or capacity becomes 0
        if (n == 0 || capacity == 0) {
            return 0;
        }
        // Return already computed result to avoid redundant calculations
        if (dp[n][capacity] != null) {
            return dp[n][capacity];
        }
        // If the current item can be included in the knapsack
        if (wt[n - 1] <= capacity) {
            //if item is to be included.Include its value as well
            dp[n][capacity] = Math.max(
                    val[n - 1] + fillKnapsack(n, capacity - wt[n - 1], wt, val, dp),
                    fillKnapsack(n - 1, capacity, wt, val, dp)
            );
        } else {
            // If the current item cannot be included, move to the next item
            dp[n][capacity] = fillKnapsack(n - 1, capacity, wt, val, dp);
        }

        return dp[n][capacity];
    }
}
