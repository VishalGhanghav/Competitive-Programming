package SdeSheetDp.src.DpOnPartition;

import java.util.Arrays;

public class MatrixChainMultiplication {
    static int matrixMultiplication(int N, int arr[]) {
        // Create a 2D dp array to store the minimum cost of multiplication
        //dp[i][j] = minimum cost (multiplications) needed to multiply matrices from index i to j (inclusive).
        int[][] dp = new int[N][N];

        // Initialize the dp array with -1 (for uncomputed values)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // Initialize the diagonal of the dp array with 0
        // Since multiplying one matrix requires 0 operations
        for (int i = 1; i < N; i++) {
            dp[i][i] = 0;
        }

        // Fill in the dp array using a bottom-up approach
        for (int i = N - 1; i >= 1; i--) {
            for (int j = i + 1; j < N; j++) {
                int minOperations = Integer.MAX_VALUE;

                // Partitioning loop to find the optimal split point
                for (int k = i; k < j; k++) {
                    // Calculate operations for multiplying matrices from i to j
                    int operations = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    minOperations = Math.min(minOperations, operations);
                }

                //dp[i][j] = minimum cost (scalar multiplications) needed to multiply matrices from index i to j (inclusive).
                dp[i][j] = minOperations;
            }
        }

        // The result is stored in dp[1][N-1]
        return dp[1][N - 1];
    }

    // Recursive function to calculate minimum number of multiplications
    // using memoization
    private static int getMatrixMultiplication(int i, int j, int[] arr, Integer[][] dp) {
        // Base case: only one matrix, so no multiplications required
        if (i == j) {
            return 0;
        }
        // Return the cached result if already computed
        if (dp[i][j] != null) {
            return dp[i][j];
        }

        int minSteps = Integer.MAX_VALUE;

        // Partitioning loop to find the optimal split point
        for (int k = i; k < j; k++) {
            // Calculate steps for multiplying matrices from i to j
            int steps = arr[i - 1] * arr[k] * arr[j] +
                    getMatrixMultiplication(i, k, arr, dp) +
                    getMatrixMultiplication(k + 1, j, arr, dp);
            minSteps = Math.min(minSteps, steps);
        }

        // Cache the computed result
        //dp[i][j] = minimum cost (multiplications) needed to multiply matrices from index i to j (inclusive).
        return dp[i][j] = minSteps;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4}; // Example matrix dimensions
        int N = arr.length;

        // Calling the matrixMultiplication function
        int minMultiplications = matrixMultiplication(N, arr);
        System.out.println("Minimum number of multiplications is: " + minMultiplications);

        // Example for memoization approach
        //dp[i][j] = minimum number of scalar multiplications required to multiply matrices from Ai ? Aj.
        Integer[][] dp = new Integer[N][N];
        int minMultiplicationsMemo = getMatrixMultiplication(1, N - 1, arr, dp);
        System.out.println("Minimum number of multiplications (Memoization) is: " + minMultiplicationsMemo);
        /*
        ? Final DP Matrix (tabular form)
        eg:arr = [10, 20, 30, 40, 30]
        N = 5
        i\j	1	2	    3	    4
        1	0	6,000	18,000	30,000
        2	—	0	    24,000	48,000
        3	—	—	    0	    36,000
        4	—	—	    —	    0
         */
    }
}
