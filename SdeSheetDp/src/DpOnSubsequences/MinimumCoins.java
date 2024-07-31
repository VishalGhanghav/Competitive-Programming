package SdeSheetDp.src.DpOnSubsequences;
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

    public static void main(String[] args) {
        MinimumCoins solution = new MinimumCoins();

        // Example 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println(solution.coinChange(coins1, amount1)); // Output: 3

        // Example 2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(solution.coinChange(coins2, amount2)); // Output: -1

        // Example 3
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println(solution.coinChange(coins3, amount3)); // Output: 0
    }
}
