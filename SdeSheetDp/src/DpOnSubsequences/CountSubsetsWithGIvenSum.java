package SdeSheetDp.src.DpOnSubsequences;

public class CountSubsetsWithGIvenSum {
    private static final int MOD = (int) Math.pow(10, 9) + 7;  // Define MOD to handle large numbers

    // Function to find the number of subsets with a given sum
    public int perfectSum(int arr[], int n, int sum) {
        Integer[][] dp = new Integer[n + 1][sum + 1];  // DP array for memoization
        int res = countSubsets(arr, n, sum, dp);  // Call the recursive function
        return res;  // Return the result
    }

    // Recursive function to count subsets with the given sum using memoization
    public static int countSubsets(int[] arr, int n, int sum, Integer[][] dp) {
        // Base case: if sum is 0, there's exactly one subset (the empty subset)
        if (n == 0 && sum == 0) {
            return 1;
        }

        // Base case: if there are no elements left and sum is greater than 0, no subset can be formed
        if (n == 0 && sum > 0) {
            return 0;
        }

        // If the result is already computed, return it
        if (dp[n][sum] != null) {
            return dp[n][sum];
        }

        // If the current element is less than or equal to the remaining sum
        if (arr[n - 1] <= sum) {
            // Count subsets including the current element and excluding the current element
            dp[n][sum] = (countSubsets(arr, n - 1, sum - arr[n - 1], dp) +
                    countSubsets(arr, n - 1, sum, dp)) % MOD;
        } else {
            // Count subsets excluding the current element
            dp[n][sum] = countSubsets(arr, n - 1, sum, dp) % MOD;
        }

        return dp[n][sum];  // Return the result
    }

    // Main method to demonstrate the perfectSum function
    public static void main(String[] args) {
        CountSubsetsWithGIvenSum ss = new CountSubsetsWithGIvenSum();

        // Test case 1
        int[] arr1 = {5, 2, 3, 10, 6, 8};
        int sum1 = 10;
        System.out.println("Number of subsets with sum " + sum1 + " is: " + ss.perfectSum(arr1, arr1.length, sum1)); // Output should be 3

        // Test case 2
        int[] arr2 = {2, 5, 1, 4, 3};
        int sum2 = 10;
        System.out.println("Number of subsets with sum " + sum2 + " is: " + ss.perfectSum(arr2, arr2.length, sum2)); // Output should be 3
    }
}
