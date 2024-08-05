package SdeSheetDp.src.BuySellStock;

import java.util.Arrays;
/*
ou are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.

Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: k = 2, prices = [2,4,1]
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.


Constraints:

1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
 */
public class BuySellStock4 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // Using tabulation
        // Creating 3D array as ind, buy, and cap are changing
        int[][][] dp = new int[n + 1][2][k + 1];

        // Base case: DP array is already initialized to 0.
        // if cap == 0 return 0. Means index and buy can be anything and cap = 0, then profit = 0
        for (int ind = 0; ind < n; ind++) {
            for (int buy = 0; buy <= 1; buy++) {
                dp[ind][buy][0] = 0;
            }
        }
        // if (ind == n) return 0
        for (int buy = 0; buy <= 1; buy++) {
            for (int cap = 0; cap <= k; cap++) {
                dp[n][buy][cap] = 0;
            }
        }

        // Main tabulation
        // Just reverse the logic for ind 0->n to n-1->0, buy can remain same, cap k->0 to 0->k
        // ind == n and cap == 0 already computed so be safe around it
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= k; cap++) {
                    // Paste recurrence and modify for dp
                    long profit = 0;
                    if (buy == 0) {
                        // We can buy or do no transaction
                        // Cap doesn't decrement as only when transaction is completed, i.e., sell is done, it will decrement
                        profit = Math.max(-prices[ind] + dp[ind + 1][1][cap],
                                0 + dp[ind + 1][buy][cap]);
                    } else {
                        // We can sell or do no transaction
                        // Cap decrement as we are selling
                        profit = Math.max(prices[ind] + dp[ind + 1][0][cap - 1],
                                0 + dp[ind + 1][buy][cap]);
                    }
                    dp[ind][buy][cap] = (int)profit;
                }
            }
        }
        // The maximum profit with k transactions is stored in dp[0][0][k].
        return dp[0][0][k];
    }

    private int getMaxProfitWithCap(int[] prices, int n, int ind, int buy, int cap, Integer[][][] dp) {
        // if cap becomes 0 or ind == n, return 0 and stop there
        if (ind == n || cap == 0) {
            return 0;
        }
        if (dp[ind][buy][cap] != null) {
            return dp[ind][buy][cap];
        }
        long profit = 0;
        if (buy == 0) {
            // We can buy or do no transaction
            // Cap doesn't decrement as only when transaction is completed, i.e., sell is done, it will decrement
            profit = Math.max(-prices[ind] + getMaxProfitWithCap(prices, n, ind + 1, 1, cap, dp),
                    0 + getMaxProfitWithCap(prices, n, ind + 1, buy, cap, dp));
        } else {
            // We can sell or do no transaction
            // Cap decrement as we are selling
            profit = Math.max(prices[ind] + getMaxProfitWithCap(prices, n, ind + 1, 0, cap - 1, dp),
                    0 + getMaxProfitWithCap(prices, n, ind + 1, buy, cap, dp));
        }
        return dp[ind][buy][cap] = (int)profit;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        BuySellStock4 sol = new BuySellStock4();

        int k = 2;
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};

        int maxProfit = sol.maxProfit(k, prices);
        System.out.println("Maximum profit with at most " + k + " transactions: " + maxProfit);
    }
}
