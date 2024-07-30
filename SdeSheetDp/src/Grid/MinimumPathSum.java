package SdeSheetDp.src.Grid;

import java.util.Arrays;

/*
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
 */
public class MinimumPathSum {
	 public int minPathSum(int[][] grid) {
	        
	     int m=grid.length;
	        int n=grid[0].length;
	        int [][]dp=new int[m][n];
	        for(int[] arr:dp){
	            Arrays.fill(arr,-1);
	        }
	       int res=uniquePathss(m-1,n-1,grid,dp);
	       return res;
	    }

	    public int uniquePathss(int m,int n,int[][] grid,int[][] dp){
	         if(m==0 && n==0){
	            return grid[0][0];
	        }
	        if(m<0 || n<0){
				//if int max value.ANy things gets added to it there is int overflow and -ve number gets in result.
				//use big value but not as big as int max value
	             return (int)Math.pow(10,9);
	        }//// If we're out of bounds, return a large value.So it gets rejected in math.min
	        //always remember base condition gets called at bottom of tree
	        if(dp[m][n]!=-1){
	            return dp[m][n];
	        }
	        int up=uniquePathss(m-1,n,grid,dp)+grid[m][n];
	        int left=uniquePathss(m,n-1,grid,dp)+grid[m][n];
	        return dp[m][n]=Math.min(up,left);
	    }
}
