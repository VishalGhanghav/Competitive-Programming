package SdeSheetDp.src.DpOnSubsequences;

public class RodCuttingProblem {

    public static void main(String[] args) {
        int[] price = {2, 5, 7, 8, 10}; // price[i] = price of rod of length (i+1)
        int n = price.length;

        // Prepare length array
        int[] length = new int[n];
        for (int i = 0; i < n; i++) length[i] = i + 1;
        // here sum is length itself.When sum gets 0 we get base case condition
        // MEMOIZATION
        Integer[][] dpMemo = new Integer[n + 1][n + 1];
        int memoAns = cutRodMemo(price, n, n, length, dpMemo);
        System.out.println("Memoization Answer: " + memoAns);

        // TABULATION
        int tabAns = cutRodTabulation(price, n, length);
        System.out.println("Tabulation Answer: " + tabAns);

        // SPACE OPTIMIZED
        int spaceAns = cutRodSpaceOptimized(price, n, length);
        System.out.println("Space Optimized Answer: " + spaceAns);
    }

    // --------------------------
    // 1) MEMOIZATION
    // --------------------------
    private static int cutRodMemo(int[] price, int n, int sum, int[] length, Integer[][] dp) {
        // Base case: no rod left or no pieces left
        if (sum == 0 || n == 0) return 0;

        if (dp[n][sum] != null) return dp[n][sum];

        if (length[n - 1] <= sum) {
            dp[n][sum] = Math.max(
                    price[n - 1] + cutRodMemo(price, n, sum - length[n - 1], length, dp), // take (unbounded)
                    cutRodMemo(price, n - 1, sum, length, dp)                               // skip
            );
        } else {
            dp[n][sum] = cutRodMemo(price, n - 1, sum, length, dp);
        }

        return dp[n][sum];
    }

    // --------------------------
    // 2) TABULATION
    // --------------------------
    private static int cutRodTabulation(int[] price, int n, int[] length) {
        int[][] dp = new int[n + 1][n + 1];

        // Base case: dp[0][*] = 0, dp[*][0] = 0 (by default zero)

        for (int i = 1; i <= n; i++) {
            for (int sum = 1; sum <= n; sum++) {
                if (length[i - 1] <= sum) {
                    dp[i][sum] = Math.max(
                            price[i - 1] + dp[i][sum - length[i - 1]], // take current piece (unbounded)
                            dp[i - 1][sum]                               // skip
                    );
                } else {
                    dp[i][sum] = dp[i - 1][sum];
                }
            }
        }

        return dp[n][n];
    }

    // --------------------------
    // 3) SPACE-OPTIMIZED (two rows)
    // --------------------------
    private static int cutRodSpaceOptimized(int[] price, int n, int[] length) {
        int[] prev = new int[n + 1];

        // Base case: prev[0..n] = 0 (by default zero)

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[n + 1];
            for (int sum = 1; sum <= n; sum++) {
                if (length[i - 1] <= sum) {
                    curr[sum] = Math.max(
                            price[i - 1] + curr[sum - length[i - 1]], // take current piece (unbounded)
                            prev[sum]                                 // skip
                    );
                } else {
                    curr[sum] = prev[sum];
                }
            }
            prev = curr;
        }

        return prev[n];
    }
}
