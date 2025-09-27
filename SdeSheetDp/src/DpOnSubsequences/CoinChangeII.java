package SdeSheetDp.src.DpOnSubsequences;

public class CoinChangeII {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        // You can switch approaches from here if needed
        // Integer[][] dp = new Integer[n+1][amount+1];
        // return memo(coins, n, amount, dp);
        // return tabulation(coins, n, amount);
        return space(coins, n, amount);
    }

    // ---------------------------------------------------
    // 1?? MEMOIZATION (Top-Down)
    // TC: O(N * amount)
    // SC: O(N * amount) + O(N) recursion stack
    // ---------------------------------------------------
    private int memo(int[] coins, int n, int amount, Integer[][] dp) {
        // base case: amount == 0 ? one way (use no coins)
        if (amount == 0) {
            return 1;
        }

        // base case: no coins but amount > 0 ? no way
        if (n == 0 && amount > 0) {
            return 0;
        }

        if (dp[n][amount] != null) {
            return dp[n][amount];
        }

        // include the current coin (unlimited use) + exclude it
        if (coins[n - 1] <= amount) {
            dp[n][amount] = memo(coins, n, amount - coins[n - 1], dp) +
                    memo(coins, n - 1, amount, dp);
        } else {
            dp[n][amount] = memo(coins, n - 1, amount, dp);
        }
        return dp[n][amount];
    }

    // ---------------------------------------------------
    // 2?? TABULATION (Bottom-Up)
    // TC: O(N * amount)
    // SC: O(N * amount)
    // ---------------------------------------------------
    private int tabulation(int[] coins, int n, int amount) {
        int[][] dp = new int[n + 1][amount + 1];

        // Base Case:
        // If amount = 0 ? only one way: choose no coin
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // If no coins ? no way to make any positive amount
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = 0;
        }

        // Fill the dp table
        for (int ind = 1; ind <= n; ind++) {
            for (int sum = 1; sum <= amount; sum++) {
                if (coins[ind - 1] <= sum) {
                    // include + exclude
                    dp[ind][sum] = dp[ind][sum - coins[ind - 1]] + dp[ind - 1][sum];
                } else {
                    dp[ind][sum] = dp[ind - 1][sum];
                }
            }
        }

        return dp[n][amount];
    }

    // ---------------------------------------------------
    // 3?? SPACE OPTIMIZATION
    // TC: O(N * amount)
    // SC: O(amount)
    // ---------------------------------------------------
    private int space(int[] coins, int n, int amount) {
        int[] prev = new int[amount + 1];
        prev[0] = 1; // base case: one way to make sum=0

        for (int ind = 1; ind <= n; ind++) {
            int[] curr = new int[amount + 1];
            curr[0] = 1;

            for (int sum = 1; sum <= amount; sum++) {
                if (coins[ind - 1] <= sum) {
                    curr[sum] = curr[sum - coins[ind - 1]] + prev[sum];
                } else {
                    curr[sum] = prev[sum];
                }
            }
            prev = curr;
        }

        return prev[amount];
    }

    // ---------------------------------------------------
    // MAIN METHOD — runs all approaches independently
    // ---------------------------------------------------
    public static void main(String[] args) {
        CoinChangeII obj = new CoinChangeII();
        int[] coins = {1, 2, 5};
        int amount = 5;

        // MEMOIZATION
        Integer[][] dp1 = new Integer[coins.length + 1][amount + 1];
        int memoAns = obj.memo(coins, coins.length, amount, dp1);
        System.out.println("Memoization Answer: " + memoAns);

        // TABULATION
        int tabAns = obj.tabulation(coins, coins.length, amount);
        System.out.println("Tabulation Answer: " + tabAns);

        // SPACE OPTIMIZATION
        int spaceAns = obj.space(coins, coins.length, amount);
        System.out.println("Space Optimized Answer: " + spaceAns);
    }
}
