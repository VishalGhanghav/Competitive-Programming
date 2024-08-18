package SdeSheetDp.src.DpOnSquares;

public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        // As square dp no need for recursion as tabulation is more intuitive
        // In this problem, copy paste the 1st row and 1st col as it is in dp array
        // For remaining elements, if 0 set to 0 in dp matrix, else do calculation
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Set dp 1st col as per matrix 1st col
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
        }

        // Set dp 1st row as per matrix 1st row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Fill the dp array based on the given conditions
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    // itself (1) + check up, left, topLeft and find minimum among them and set in dp[][]
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }

        // Calculate sum of all elements in dp array for final result
        // We could have also calculated in above loop itself
        int sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum += dp[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        CountSquareSubmatricesWithAllOnes obj = new CountSquareSubmatricesWithAllOnes();

        // Example test case
        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        int result = obj.countSquares(matrix);

        // Output the result
        System.out.println("The total number of square submatrices is: " + result);
    }
}
