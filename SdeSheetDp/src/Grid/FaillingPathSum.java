package SdeSheetDp.src.Grid;

public class FaillingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int minSum = Integer.MAX_VALUE;
        int n = matrix.length;
        Integer[][] dp = new Integer[n][n];

        // Try starting from every element in the first row
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, getMinFallingPathSum(0, j, matrix, dp));
        }

        return minSum;
    }

    public int getMinFallingPathSum(int m, int n, int[][] matrix, Integer[][] dp) {
        int size = matrix.length;

        // If out of bounds, return a large value
        if (n < 0 || n >= size) {
            return (int) Math.pow(10, 5);
        }

        // Base case: if we are at the last row, return the element
        if (m == size - 1) {
            return matrix[m][n];
        }

        // If already found
        if (dp[m][n] != null) {
            return dp[m][n];
        }

        // Recursive case: compute the minimum path sum for the current position
        int leftDiag = getMinFallingPathSum(m + 1, n - 1, matrix, dp) + matrix[m][n];
        int down = getMinFallingPathSum(m + 1, n, matrix, dp) + matrix[m][n];
        int rightDiag = getMinFallingPathSum(m + 1, n + 1, matrix, dp) + matrix[m][n];

        // Return the minimum sum including the current element
        return dp[m][n] = Math.min(leftDiag, Math.min(down, rightDiag));
    }

    public static void main(String[] args) {
        FaillingPathSum fps = new FaillingPathSum();

        int[][] matrix = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        int result = fps.minFallingPathSum(matrix);
        System.out.println("Minimum Falling Path Sum: " + result);
    }
}
