package SdeSheetDp.src.BuySellStock;

import java.net.StandardSocketOptions;
import java.util.Arrays;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
 */
public class BuySellStock3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Using tabulation
        // Creating 3D array as ind, buy, and cap are changing
        int[][][] dp = new int[n + 1][2][3];

        // Base case: DP array is already initialized to 0.
        // if cap == 0 return 0. Means index and buy can be anything and cap = 0, then profit = 0
        for (int ind = 0; ind < n; ind++) {
            for (int buy = 0; buy <= 1; buy++) {
                dp[ind][buy][0] = 0;
            }
        }
        // if (ind == n) return 0
        for (int buy = 0; buy <= 1; buy++) {
            for (int cap = 0; cap <= 2; cap++) {
                dp[n][buy][cap] = 0;
            }
        }

        // Main tabulation
        // Just reverse the logic for ind 0->n to n-1->0, buy can remain same, cap 2->0 to 0->2
        // ind == n and cap == 0 already computed so be safe around it
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    // Paste recurrence and modify for dp
                    int profit = 0;
                    if (buy == 0) {
                        // We can buy or do no transaction
                        // Cap doesn't decrement as only when transaction is completed, i.e., sell is done, it will decrement
                        profit = Math.max(-prices[ind] + dp[ind + 1][1][cap],
                                0 + dp[ind + 1][buy][cap]);
                    } else {
                        // We can sell or do no transaction
                        // Cap decrement as well as selling
                        profit = Math.max(prices[ind] + dp[ind + 1][0][cap - 1],
                                0 + dp[ind + 1][buy][cap]);
                    }
                    dp[ind][buy][cap] = profit;
                }
            }
        }
        // The maximum profit with 2 transactions is stored in dp[0][0][2].
        return dp[0][0][2];
    }

    private int getMaxProfitWithCap(int[] prices, int n, int ind, int buy, int cap, Integer[][][] dp) {
        // if cap becomes 0 or ind == n, return 0 and stop there
        if (ind == n || cap == 0) {
            return 0;
        }
        if (dp[ind][buy][cap] != null) {
            return dp[ind][buy][cap];
        }
        int profit = 0;
        if (buy == 0) {
            // We can buy or do no transaction
            // Cap doesn't decrement as only when transaction is completed, i.e., sell is done, it will decrement
            profit = Math.max(-prices[ind] + getMaxProfitWithCap(prices, n, ind + 1, 1, cap, dp),
                    0 + getMaxProfitWithCap(prices, n, ind + 1, buy, cap, dp));
        } else {
            // We can sell or do no transaction
            // Cap decrement as well as selling
            profit = Math.max(prices[ind] + getMaxProfitWithCap(prices, n, ind + 1, 0, cap - 1, dp),
                    0 + getMaxProfitWithCap(prices, n, ind + 1, buy, cap, dp));
        }
        return dp[ind][buy][cap] = profit;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        BuySellStock3 sol = new BuySellStock3();

        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};//op 6

        int maxProfit = sol.maxProfit(prices);
        System.out.println("Maximum profit with at most 2 transactions: " + maxProfit);
    }
}
