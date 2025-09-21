package SdeSheetDp.src.Grid;

import java.util.Arrays;

/*
 There is a robot on an m x n grid. The robot is initially located at the top-left corner 
 (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
  The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take
 to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.

 

Example 1:


Input: m = 3, n = 7
Output: 28
Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right 
corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
 

Constraints:

1 <= m, n <= 100
 */
public class UniquePaths1 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int res = uniquePathss(m - 1, n - 1, dp);
        return res;
    }

    public int uniquePathss(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) {
            return 1;
        }
        if (m < 0 || n < 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        int up = uniquePathss(m - 1, n, dp);
        int left = uniquePathss(m, n - 1, dp);
        return dp[m][n] = up + left;
    }

    private int tabulation(int m, int n, int[][] dp) {
        //base
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // for i=0 and j=0 ,We have base case so set 1
                if (i == 0 && j == 0) {
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

    private int space(int m, int n) {
        int[] prev = new int[n];
        for (int i = 0; i < m; i++) {
            // Create a temporary array to store the results for the current row
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
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
