package SdeSheetDp.src.OneDimensionDp;

/*
 You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 

Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 

Constraints:

1 <= n <= 45
 */
public class ClimbStairs {
/*
 class Solution {
    public int climbStairs(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        int res=stairsWays(n,dp);
        return res;
    }

    public int stairsWays(int n,int[] dp){
        if(n==0 || n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n]=stairsWays(n-1,dp)+stairsWays(n-2,dp);
    }

    /* public int stairsWays(int n,int[] dp){
        if(n==0 || n==1){
            return 1;
        }
        if(dp[n]!=-1){
            return dp[n];
        }
        return dp[n]=stairsWays(n-1,dp)+stairsWays(n-2,dp);
    }*/

    //tabular
    /*private int stairsWays(int n,int[] dp) {
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }*/

    //optimization
    /*private int stairsWays(int n,int[] dp) {
        //We see we only need to calculate previous 2 elements.
        //Hence use prev1 and prev 2
        int prev1 = 1;//dp[0]
        int prev2 = 1;//dp[1]
        for(int i=2;i<=n;i++) {
            int curr = prev1 + prev2;
            prev1 = prev2;
            prev2 = curr;
        }
        return prev2;
    }*/
}
