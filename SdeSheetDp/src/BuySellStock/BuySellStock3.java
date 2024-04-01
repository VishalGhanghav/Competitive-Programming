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
    public static int compute(int[] arr,int n,int index,int buy,int cap,int[][][] dp){
        if(index==n || cap==0){
            return 0;
        }
        int op1 = 0;
        int op2 = 0;
        if(dp[index][buy][cap]!=-1){
            return dp[index][buy][cap];
        }
        if(buy==0){
            //buying
            op1=-arr[index]+compute(arr,n,index+1,1,cap,dp);
            //no transaction
            op2=0+compute(arr,n,index+1,0,cap,dp);
        }
        if(buy==1){
            //selling
            op1=arr[index]+compute(arr,n,index+1,0,cap-1,dp);
            //no transaction
            op2=0+compute(arr,n,index+1,1,cap,dp);
        }
        return dp[index][buy][cap]=Math.max(op1,op2);

    }

    public static void main(String[] args) {
        int []arr=new int[]{3,3,5,0,0,3,1,4};
        int size=arr.length;
        int dp[][][]=new int[size][2][3];
        for(int i=0;i<size;i++){
            for(int j=0;j<2;j++){
               Arrays.fill(dp[i][j],-1);
            }
        }
        System.out.println("maxProfit "+compute(arr,size,0,0,2,dp));
    }
}
