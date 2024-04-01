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
    public static int compute(int[] arr,int n,int index,int buy,int[][] dp){
        if(index>=n){
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
            op1=arr[index]+compute(arr,n,index+2,0,dp);
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
