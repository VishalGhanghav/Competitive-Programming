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

    public static int compute(int[] arr,int n,int index,int buy,int[][] dp){
        if(index==n){
            return 0;
        }
        int op1 = 0;
        int op2 = 0;
        if(dp[index][buy]!=-1){
            return dp[index][buy];
        }
        if(buy==0){
            //buying
            op1=-arr[index]+compute(arr,n,index+1,1,dp);
            //no transaction
            op2=0+compute(arr,n,index+1,0,dp);
        }
        if(buy==1){
            //selling
            op1=arr[index]+compute(arr,n,index+1,0,dp);
            //no transaction
            op2=0+compute(arr,n,index+1,1,dp);
        }
        return dp[index][buy]=Math.max(op1,op2);

    }

    public static void main(String[] args) {
        int []arr=new int[]{7,1,5,3,6,4};
        int size=arr.length;
        int dp[][]=new int[size][2];
        for(int []pq:dp){
            Arrays.fill(pq,-1);
        }
        System.out.println("maxProfit "+compute(arr,size,0,0,dp));
    }
}
