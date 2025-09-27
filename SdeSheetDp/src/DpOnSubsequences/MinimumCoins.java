package SdeSheetDp.src.DpOnSubsequences;

import java.util.Arrays;

/*
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
public class MinimumCoins {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        Integer[][] dp = new Integer[n + 1][amount + 1]; // Memoization array to store results of subproblems
        int res = coinChangeProblemMinNoOfCoinsRecursion(coins, amount, n, dp);
        // If the result is Integer.MAX_VALUE - 1, it means no solution was found, so return -1
        return res == Integer.MAX_VALUE - 1 ? -1 : res;
    }

    private int coinChangeProblemMinNoOfCoinsRecursion(int[] arr, int sum, int n, Integer[][] dp) {
        // Base case: If no coins are left
        if (n == 0) {
            // If sum is 0, no coins are needed
            if (sum == 0) {
                return 0;
            } else {
                // If sum is not 0, return a large number to indicate an impossible result
                return Integer.MAX_VALUE - 1;
            }
        }

        // If the result for this state is already computed, return it
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // If the coin can be included in the solution
        if (arr[n - 1] <= sum) {
            // Calculate the result by including the coin and by excluding the coin
            int include = coinChangeProblemMinNoOfCoinsRecursion(arr, sum - arr[n - 1], n, dp) + 1;
            int exclude = coinChangeProblemMinNoOfCoinsRecursion(arr, sum, n - 1, dp);
            // Store the minimum of including and excluding the coin
            dp[n][sum] = Math.min(include, exclude);
            /*
            return dp[n][sum]=Math.min(coinChangeProblemMinNoOfCoinsRecursion(arr, sum-arr[n-1], n,dp)+1 ,
					coinChangeProblemMinNoOfCoinsRecursion(arr, sum, n-1,dp));
             */
        } else {
            // If the coin cannot be included, just move to the next coin
            dp[n][sum] = coinChangeProblemMinNoOfCoinsRecursion(arr, sum, n - 1, dp);
        }

        // Return the computed result
        return dp[n][sum];
    }

    // ? Tabulation
    private int coinChangeTabulation(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int j = 0; j <= amount; j++) dp[0][j] = Integer.MAX_VALUE - 1;
        for (int i = 0; i <= n; i++) dp[i][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j)
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i - 1]], dp[i - 1][j]);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[n][amount] == Integer.MAX_VALUE - 1 ? -1 : dp[n][amount];
    }

    // ? Space Optimized (1D)
    private int coinChangeSpaceOptimized(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], 1 + dp[j - coins[i]]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE - 1 ? -1 : dp[amount];
    }

    // ? Two Array Version
    private int coinChangeTwoArray(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];
        Arrays.fill(prev, Integer.MAX_VALUE - 1);
        prev[0] = 0;

        for (int i = 1; i <= n; i++) {
            curr[0] = 0;
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    int include = (curr[j - coins[i - 1]] == Integer.MAX_VALUE - 1)
                            ? Integer.MAX_VALUE - 1
                            : 1 + curr[j - coins[i - 1]];
                    int exclude = prev[j];
                    curr[j] = Math.min(include, exclude);
                } else {
                    curr[j] = prev[j];
                }
            }
            prev = curr.clone();
        }

        return prev[amount] == Integer.MAX_VALUE - 1 ? -1 : prev[amount];
    }

    // ? Main to test all
    public static void main(String[] args) {
        MinimumCoins obj = new MinimumCoins();
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Memoization: " + obj.coinChange(coins, amount));
        System.out.println("Tabulation: " + obj.coinChangeTabulation(coins, amount));
        System.out.println("Two Array Version: " + obj.coinChangeTwoArray(coins, amount));
        System.out.println("Space Optimized (1D): " + obj.coinChangeSpaceOptimized(coins, amount));
    }
}
