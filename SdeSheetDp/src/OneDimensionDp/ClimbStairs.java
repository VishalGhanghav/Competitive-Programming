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
}
 */
}
