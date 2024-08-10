package SdeSheetDp.src.BuySellStock;

import java.util.Arrays;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).



Example 1:

Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
Example 2:

Input: prices = [1]
Output: 0


Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
 */
public class BuySellStockCooldown {
    public int maxProfit(int[] prices) {
        int pricesLength = prices.length;

        // Memoization (commented out for tabulation approach)
        // Integer[][] dp = new Integer[pricesLength + 1][2];
        // return getMaxProfit(0, 0, prices, pricesLength, dp);

        // Tabulation approach
        // We use pricesLength + 2 because of the cooldown period.
        // When selling, we skip one day (ind + 2), so we need extra space to avoid out-of-bounds errors.
        int[][] dp = new int[pricesLength + 2][2];

        // Base case: DP array is already initialized to 0, but for understanding we write base cases
        dp[pricesLength][0] = 0; // If we reach the end of the prices array, profit is 0
        dp[pricesLength][1] = 0; // Same for the case where we hold a stock

        // Fill the DP table in reverse order (bottom-up approach)
        for (int ind = pricesLength - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 0) {
                    // If we are not holding a stock, we have the option to buy or not
                    profit = Math.max(-prices[ind] + dp[ind + 1][1], dp[ind + 1][buy]);
                } else {
                    // If we are holding a stock, we have the option to sell or not
                    // After selling, we skip one day (ind + 2) due to the cooldown period
                    profit = Math.max(prices[ind] + dp[ind + 2][0], dp[ind + 1][buy]);
                }
                dp[ind][buy] = (int) profit;
            }
        }
        // The maximum profit is found at the start of the prices array with no stock held
        return dp[0][0];
    }

    private int getMaxProfit(int ind, int buy, int[] prices, int n, Integer[][] dp) {
        // Base case
        // As ind + 2 can pass n, we check if ind >= n.
        // If we reach or exceed the end of the array, no more profit can be earned.
        if (ind >= n) {
            return 0;
        }
        if (dp[ind][buy] != null) {
            return dp[ind][buy];
        }
        long profit = 0;
        // If buy == 0, we can buy or do no transaction
        if (buy == 0) {
            profit = Math.max(-prices[ind] + getMaxProfit(ind + 1, 1, prices, n, dp),
                    0 + getMaxProfit(ind + 1, buy, prices, n, dp));
        }
        // If buy == 1, we can sell or do no transaction
        // After selling, we cannot buy for one day (ind + 2)
        if (buy == 1) {
            profit = Math.max(prices[ind] + getMaxProfit(ind + 2, 0, prices, n, dp),
                    0 + getMaxProfit(ind + 1, buy, prices, n, dp));
        }
        return dp[ind][buy] = (int) profit;
    }

    public static void main(String[] args) {
        BuySellStockCooldown sol = new BuySellStockCooldown();

        int[] prices = {1, 2, 3, 0, 2};

        int maxProfit = sol.maxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit);
    }
}
