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
    public static int compute(int[] arr,int n,int index,int buy,int[][] dp,int fee){
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
            op1=-arr[index]+compute(arr,n,index+1,1,dp,fee);
            //no transaction
            op2=0+compute(arr,n,index+1,0,dp,fee);
        }
        if(buy==1){
            //selling
            op1=arr[index]-fee+compute(arr,n,index+1,0,dp,fee);
            //no transaction
            op2=0+compute(arr,n,index+1,1,dp,fee);
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
        int fee=2;
        System.out.println("maxProfit "+compute(arr,size,0,0,dp,fee));
    }
}
