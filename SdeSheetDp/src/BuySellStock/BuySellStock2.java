package SdeSheetDp.src.BuySellStock;

import java.util.Arrays;
/*
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.

Find and return the maximum profit you can achieve.



Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.


Constraints:

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 */
public class BuySellStock2 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Uncomment the below lines for memoization approach
        // // dp array for memoization, size is 2 because the buy flag can be 0 or 1
        // Integer[][] dp = new Integer[n][2];
        // // We start from index 0 and go up to n
        // return getMaxProfit(prices, n, 0, 0, dp);

        // Tabulation approach
        int[][] dp = new int[n + 1][2];

        // Base case: If index reaches n, profit is 0
        dp[n][0] = 0;
        dp[n][1] = 0;

        // Fill the dp array from n-1 to 0
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit = 0;
                if (buy == 0) {
                    // We can buy or do no transaction. We pick whichever gives maximum profit.
                    // In buy case, we will set buyFlag to 1 which means now we hold a share.
                    profit = Math.max(-prices[ind] + dp[ind + 1][1], 0 + dp[ind + 1][buy]);
                } else {
                    // We are already holding a stock
                    // We can sell or do no transaction. We pick whichever gives maximum profit.
                    // In sell case, we will set buyFlag to 0 which means now we sold the share.
                    profit = Math.max(prices[ind] + dp[ind + 1][0], 0 + dp[ind + 1][buy]);
                }
                dp[ind][buy] = profit;
            }
        }
        return dp[0][0];
    }

    private int getMaxProfit(int[] prices, int n, int ind, int buy, Integer[][] dp) {
        // If we reach the last index, no more profit can be earned
        // it means we have finished trading on all days, and there is no
        // more money that we can get, therefore we simply return 0.
        if (ind == n) {
            return 0;
        }
        // If the subproblem is already solved, return the stored result
        if (dp[ind][buy] != null) {
            return dp[ind][buy];
        }

        int profit = 0;
        if (buy == 0) {
            // If buy == 0, it means we haven't bought anything, we can buy
            // We can buy or do no transaction. We pick whichever gives maximum profit.
            // In buy case, we will set buyFlag to 1 which means now we hold a share.
            profit = Math.max(-prices[ind] + getMaxProfit(prices, n, ind + 1, 1, dp),
                    0 + getMaxProfit(prices, n, ind + 1, buy, dp));
        } else {
            // We are already holding a stock
            // We can sell or do no transaction. We pick whichever gives maximum profit.
            // In sell case, we will set buyFlag to 0 which means now we sold the share.
            profit = Math.max(prices[ind] + getMaxProfit(prices, n, ind + 1, 0, dp),
                    0 + getMaxProfit(prices, n, ind + 1, buy, dp));
        }
        return dp[ind][buy] = profit;
    }

    static long space(long[] Arr, int n) {
        // Create arrays 'ahead' and 'cur' to store the maximum profit ahead and current profit
        long[] ahead = new long[2];
        long[] cur = new long[2];

        // Base condition: If we have no stocks to buy or sell, profit is 0
        ahead[0] = ahead[1] = 0;

        long profit = 0;

        // Iterate through the array in reverse to calculate the maximum profit
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                if (buy == 0) { // We can buy the stock
                    profit = Math.max(0 + ahead[0], -Arr[ind] + ahead[1]);
                }

                if (buy == 1) { // We can sell the stock
                    profit = Math.max(0 + ahead[1], Arr[ind] + ahead[0]);
                }
                cur[buy] = profit;
            }

            // Update the 'ahead' array with the current profit values
            System.arraycopy(cur, 0, ahead, 0, 2);
        }
        return cur[0]; // The maximum profit is stored in 'cur[0]'
    }

    // Main method to test the solution
    public static void main(String[] args) {
        BuySellStock2 sol = new BuySellStock2();

        int[] prices = {7, 1, 5, 3, 6, 4};//op :7

        int maxProfit = sol.maxProfit(prices);
        System.out.println("Maximum profit: " + maxProfit);
    }
}
