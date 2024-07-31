package SdeSheetDp.src.DpOnSubsequences;

public class RodCuttingProblem {
    public static void main(String[] args) {
        // Example 1
        int[] price1 = {1, 5, 8, 9, 10, 17, 17, 20};
        int N1 = 8;
        //2 rods or 8 price with length 3 and 1 rod of price 5 with length 2
        System.out.println(cutRod(price1, N1)); // Expected output: 22

        // Example 2
        int[] price2 = {3, 5, 8, 9, 10, 17, 17, 20};
        int N2 = 8;
        System.out.println(cutRod(price2, N2)); // Expected output: 24
    }

    public static int cutRod(int price[], int N) {
        // Initialize a 2D DP array for memoization
        Integer[][] dp = new Integer[N + 1][N + 1];
        int[] length = new int[N];
        for (int i = 0; i < N; i++) {
            length[i] = i + 1;
        }
        int sum = length.length; // sum and n are same in this case

        // Start the recursive function with memoization
        return cutRodMemo(price, N, sum, length, dp);
    }

    private static int cutRodMemo(int[] price, int n, int sum, int[] length, Integer[][] dp) {
        // Base case: If the rod length is 0 or no pieces are left, no value can be obtained
        if (sum == 0 || n == 0) {
            return 0;
        }

        // If the result is already computed, return it
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // If the current piece length is less than or equal to the remaining length
        if (length[n - 1] <= sum) {
            // Max value obtained by either cutting or not cutting the current piece
            dp[n][sum] = Math.max(price[n - 1] + cutRodMemo(price, n, sum - length[n - 1], length, dp),
                    cutRodMemo(price, n - 1, sum, length, dp));
        } else {
            // If the current piece length is greater than the remaining length, skip it
            dp[n][sum] = cutRodMemo(price, n - 1, sum, length, dp);
        }

        return dp[n][sum];
    }
}
