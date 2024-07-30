package SdeSheetDp.src.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.



Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10


Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 */
public class MinimumPathSumTriangular {
    // we are not given m,n .we are given list of list.
    // We just need to reach bottom row and not bottom right.
    // So start recursion from start
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();

        // recursive solution will give exponential tc. 2^m*n
        // So memoize it
        // m, n changing so 2d dp needed
        int[][] dp = new int[m][m];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        return getMinimumTotal(0, 0, triangle, dp);
    }

    public int getMinimumTotal(int m, int n, List<List<Integer>> triangle, int[][] dp) {
        if (m == triangle.size() - 1) {
            return triangle.get(m).get(n);
        }
        // if already found why calculate again
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        // we can go down or diagonally. keep adding sum when we traverse the path
        int down = getMinimumTotal(m + 1, n, triangle, dp) + triangle.get(m).get(n);
        int diag = getMinimumTotal(m + 1, n + 1, triangle, dp) + triangle.get(m).get(n);
        // we need to find minimum path sum so do
        return dp[m][n] = Math.min(down, diag);
    }

    public static void main(String[] args) {
        MinimumPathSumTriangular  tps = new MinimumPathSumTriangular();

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        int result = tps.minimumTotal(triangle);
        System.out.println("Minimum path sum: " + result);//2 3 5 1
    }
}
