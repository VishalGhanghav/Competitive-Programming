package SdeSheetDp.src.BuySellStock;

import java.util.Arrays;
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
The transaction fee is only charged once for each stock purchase and sale.


Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6


Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 */
public class BuySellStockTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        // I need to pay a fee for each transaction.
        // This means while selling, we need to remove the fee from the profit we earn.
        int pricesLength = prices.length;

        // Memoization (commented out for tabulation approach)
        // Integer[][] dp = new Integer[pricesLength + 1][2];
        // return getMaxProfit(0, 0, fee, prices, pricesLength, dp);

        // Tabulation approach
        int[][] dp = new int[pricesLength + 1][2];

        // Base case: DP array is already initialized to 0, but for understanding we write base cases
        // If we have processed all days, the profit is 0
        dp[pricesLength][0] = 0;
        dp[pricesLength][1] = 0;

        // We fill the dp array from the end towards the beginning (reverse order of the recursion)
        for (int ind = pricesLength - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                long profit = 0;
                if (buy == 0) {
                    // If we are in a state where we can buy, we either buy or skip
                    profit = Math.max(-prices[ind] + dp[ind + 1][1], dp[ind + 1][buy]);
                } else {
                    // If we are in a state where we can sell, we either sell or skip
                    // After selling, we deduct the transaction fee from the profit
                    profit = Math.max(prices[ind] - fee + dp[ind + 1][0], dp[ind + 1][buy]);
                }
                dp[ind][buy] = (int) profit;
            }
        }
        return dp[0][0];
    }

    private int getMaxProfit(int ind, int buy, int fee, int[] prices, int n, Integer[][] dp) {
        // Base case
        // When we reach the last index, we cannot earn any profit
        if (ind == n) {
            return 0;
        }
        if (dp[ind][buy] != null) {
            return dp[ind][buy];
        }
        long profit = 0;
        // If buy == 0, we can buy or do no transaction
        if (buy == 0) {
            profit = Math.max(-prices[ind] + getMaxProfit(ind + 1, 1, fee, prices, n, dp),
                    0 + getMaxProfit(ind + 1, buy, fee, prices, n, dp));
        }
        // If buy == 1, we can sell or do no transaction
        // After selling, we have to pay the fee as the transaction is complete
        if (buy == 1) {
            profit = Math.max(prices[ind] - fee + getMaxProfit(ind + 1, 0, fee, prices, n, dp),
                    0 + getMaxProfit(ind + 1, buy, fee, prices, n, dp));
        }
        return dp[ind][buy] = (int) profit;
    }

    public static void main(String[] args) {
        BuySellStockTransactionFee solution = new BuySellStockTransactionFee();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        int maxProfit = solution.maxProfit(prices, fee);
        System.out.println("Max Profit: " + maxProfit);
    }
}
