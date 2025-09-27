package SdeSheetDp.src.DpOnSubsequences;

public class UnboundedKnapsack {
    // ? Main method for testing all approaches independently
    public static void main(String[] args) {
        int N = 4; // Number of items
        int W = 8; // Maximum weight capacity of knapsack
        int[] val = {6, 1, 7, 7}; // Values of items
        int[] wt = {1, 3, 4, 5};  // Weights of items

        System.out.println("Memoization: " + knapSackMemo(N, W, val, wt));
        System.out.println("Tabulation: " + knapSackTabulation(N, W, val, wt));
        System.out.println("Space Optimized: " + knapSackSpaceOptimized(N, W, val, wt));
    }

    // ------------------------------------------------------------------------------------
    // ? 1. Memoization (Top-Down DP)
    // ------------------------------------------------------------------------------------
    public static int knapSackMemo(int N, int W, int[] val, int[] wt) {
        Integer[][] dp = new Integer[N + 1][W + 1];
        return solveMemo(N, W, wt, val, dp);
    }

    private static int solveMemo(int n, int capacity, int[] wt, int[] val, Integer[][] dp) {
        // Base Case:
        // If no items or capacity 0, no value can be taken.
        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (dp[n][capacity] != null) {
            return dp[n][capacity];
        }

        if (wt[n - 1] <= capacity) {
            // ? Since it's unbounded, we can include the same item again (n stays same)
            dp[n][capacity] = Math.max(
                    val[n - 1] + solveMemo(n, capacity - wt[n - 1], wt, val, dp),
                    solveMemo(n - 1, capacity, wt, val, dp)
            );
        } else {
            dp[n][capacity] = solveMemo(n - 1, capacity, wt, val, dp);
        }

        return dp[n][capacity];
    }

    // ------------------------------------------------------------------------------------
    // ? 2. Tabulation (Bottom-Up DP)
    // ------------------------------------------------------------------------------------
    public static int knapSackTabulation(int N, int W, int[] val, int[] wt) {
        int[][] dp = new int[N + 1][W + 1];

        // Base case explanation:
        // dp[0][*] = 0 ? If there are 0 items, we cannot take anything.
        // dp[*][0] = 0 ? If capacity = 0, we cannot take any items.
        // These are implicitly 0 because Java initializes arrays to 0 by default.

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    // Take or not take (unbounded ? stay on same i)
                    dp[i][w] = Math.max(
                            val[i - 1] + dp[i][w - wt[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[N][W];
    }

    // ------------------------------------------------------------------------------------
    // ? 3. Space Optimization (Using two arrays)
    // ------------------------------------------------------------------------------------
    public static int knapSackSpaceOptimized(int N, int W, int[] val, int[] wt) {
        int[] prev = new int[W + 1];

        // Base case:
        // prev[0] = 0 ? If capacity = 0, value = 0. (Already 0 by default)

        for (int i = 1; i <= N; i++) {
            int[] curr = new int[W + 1];
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    curr[w] = Math.max(
                            val[i - 1] + curr[w - wt[i - 1]],  // include current (stay in same i)
                            prev[w]                              // exclude
                    );
                } else {
                    curr[w] = prev[w];
                }
            }
            prev = curr;
        }
        return prev[W];
    }
}
