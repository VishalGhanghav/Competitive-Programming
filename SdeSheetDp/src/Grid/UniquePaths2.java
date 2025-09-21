package Grid;

import java.util.Arrays;

/*
 * You are given an m x n integer array grid. There is a robot initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include any square that is an obstacle.

Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The testcases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.
 */
public class UniquePaths2 {
	 public int uniquePathsWithObstacles(int[][] obstacleGrid) {
	        int m=obstacleGrid.length;
	        int n=obstacleGrid[0].length;
	        int [][]dp=new int[m][n];
	        for(int[] arr:dp){
	            Arrays.fill(arr,-1);
	        }
	       int res=uniquePathss(m-1,n-1,obstacleGrid,dp);
	       return res;
	    }

	    public int uniquePathss(int m,int n,int[][] obstacleGrid,int[][] dp){
	        if(m<0 || n<0 || obstacleGrid[m][n]==1){
	            return 0;
	        }
	         if(m==0 && n==0){
	            return 1;
	        }
	        
	        if(dp[m][n]!=-1){
	            return dp[m][n];
	        }
	        int up=uniquePathss(m-1,n,obstacleGrid,dp);
	        int left=uniquePathss(m,n-1,obstacleGrid,dp);
	        return dp[m][n]=up+left;
	    }

	private int tabulation(int m, int n, int[][] dp, int[][] obstacle) {

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// mat[i][j]==1, we will simply mark dp[i][j] = 0,
				// it means that this cell is a blocked one and no path is possible through it.
				if(obstacle[i][j]==1) {
					dp[i][j]=0;
					continue;
				}
				// for i=0 and j=0 ,We have base case so set 1
				if (i == 0 && j == 0) {
					dp[i][j] = 1;//One path found
					continue;
				}

				int up = 0;
				int left = 0;
				// Calculate the number of ways by moving up (if possible) and left (if possible)
				if (i > 0)
					up = dp[i - 1][j];
				if (j > 0)
					left = dp[i][j - 1];
				// Store the total number of ways to reach the current cell in the DP array
				dp[i][j] = up + left;
			}
		}
		return dp[m - 1][n - 1];
	}

	//If we observe,We only need up and left.
	//ie basically current row and previous row only needed

	private int space(int m, int n,int[][] obstacle) {
		int[] prev = new int[n];
		for (int i = 0; i < m; i++) {
			// Create a temporary array to store the results for the current row
			int[] temp = new int[n];
			for (int j = 0; j < n; j++) {
				if(obstacle[i][j] == 1) {
					temp[j] = 0;
					continue;
				}
				if (i == 0 && j == 0) {
					// Base condition: There's one way to reach the top-left cell (0, 0)
					temp[j] = 1;
					continue;
				}
				int up = 0;
				int left = 0;
				// Calculate the number of ways by moving up (if possible) and left (if possible)
				if (i > 0)
					up = prev[j];
				if (j > 0)
					left = temp[j - 1];
				// Store the total number of ways to reach the current cell in the DP array
				temp[j] = up + left;
			}
			//Set prev as temp array calculated just above
			prev = temp;
		}
		return prev[n - 1];
	}
}
